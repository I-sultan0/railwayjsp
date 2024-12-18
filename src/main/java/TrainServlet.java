import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TrainServlet")
public class TrainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Redirect GET requests to the registration page (or handle them appropriately)
		response.sendRedirect("welcome.jsp"); // Redirect to the registration form
	}

	// Database connection details
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Retrieve form data
		String fromStation = request.getParameter("fromStation");
		String toStation = request.getParameter("toStation");
		String journeyDate = request.getParameter("journeyDate");

		// Log the parameters received
		System.out.println("From Station: " + fromStation);
		System.out.println("To Station: " + toStation);
		System.out.println("Journey Date: " + journeyDate);

		// Validate inputs
		if (fromStation == null || toStation == null || fromStation.isEmpty() || toStation.isEmpty()) {
			out.println("<h3>Error: Please fill in all fields!</h3>");
			return;
		}

		// List to store train data
		List<Train> trainList = new ArrayList<>();

		try {
			// Load Oracle JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish connection to the database
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123");

			// Base SQL query
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM trains WHERE FROM_STATION = '")
			   .append(toStation)
			   .append("' AND TO_STATION = '")
			   .append(fromStation)
			   .append("'");
			
			// Check if journeyDate is provided and add it to the query if so
			if (journeyDate != null && !journeyDate.isEmpty()) {
				sql.append(" AND JOURNEY_DATE=");
				sql.append("'");
				sql.append("25-DEC-24");
				sql.append("'");
				sql.append(";");

			}

			// Log the final SQL query
			System.out.println("SQL Query: " + sql);

			PreparedStatement pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, fromStation);
			pstmt.setString(2, toStation);

			// If journey date is provided, set it in the prepared statement
			if (journeyDate != null && !journeyDate.isEmpty()) {
				pstmt.setString(3, journeyDate);
			}

			// Execute the query
			ResultSet rs = pstmt.executeQuery();

			// Iterate through the result set and populate the train list
			while (rs.next()) {
				int trainId = rs.getInt("train_id");
				String trainName = rs.getString("train_name");
				String fromStationDb = rs.getString("from_station");
				String toStationDb = rs.getString("to_station");
				Date journeyDateDb = rs.getDate("journey_date");

				// Add train to the list
				trainList.add(new Train(trainId, trainName, fromStationDb, toStationDb, journeyDateDb));
			}

			// Close resources
			rs.close();
			pstmt.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			out.println("<h3>Error: Unable to load database driver!</h3>");
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h3>Error: Unable to query database!</h3>");
		}

		// If no trains are found, show a message
		if (trainList.isEmpty()) {
			out.println("<h3>No trains found for the specified search criteria!</h3>");
			return;
		}

		// Pass the train list to the JSP page
		request.setAttribute("trainList", trainList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("trainResults.jsp");
		dispatcher.forward(request, response);
	}

	// Train class to hold train data
	static class Train {
		private int trainId;
		private String trainName;
		private String fromStation;
		private String toStation;
		private Date journeyDate;

		public Train(int trainId, String trainName, String fromStation, String toStation, Date journeyDate) {
			this.trainId = trainId;
			this.trainName = trainName;
			this.fromStation = fromStation;
			this.toStation = toStation;
			this.journeyDate = journeyDate;
		}

		public int getTrainId() {
			return trainId;
		}

		public String getTrainName() {
			return trainName;
		}

		public String getFromStation() {
			return fromStation;
		}

		public String getToStation() {
			return toStation;
		}

		public Date getJourneyDate() {
			return journeyDate;
		}
	}
}