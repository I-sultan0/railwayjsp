import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TrainBox")
public class TrainBox extends HttpServlet {
    private static final long serialVersionUID = 1L;
	
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Redirect GET requests to the registration page (or handle them appropriately)
		response.sendRedirect("welcome.jsp"); // Redirect to the registration form
	}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Set response content type
    response.setContentType("text/html;charset-UTF-8");
    PrintWriter out = response.getWriter();

    // Get parameters from the request
    String fromStation = request.getParameter("fromStation");
    String toStation = request.getParameter("toStation");
    String journeyDate = request.getParameter("journeyDate");

    // Establish connection to the database
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String sql = "SELECT * FROM trains;";
        // Base SQL query
//        StringBuilder sql = new StringBuilder();
//		sql.append("SELECT * FROM trains");
//		WHERE FROM_STATION = '")
//		   .append(toStation)
//		   .append("' AND TO_STATION = '")
//		   .append(fromStation)
//		   .append("'");
		
		// Check if journeyDate is provided and add it to the query if so
		if (journeyDate != null && !journeyDate.isEmpty()) {
//			sql.append(" AND JOURNEY_DATE=");
//			sql.append("'");
//			sql.append("25-DEC-24");
//			sql.append("'");
//			sql.append(";");

		}

        // Log the final SQL query
        System.out.println("SQL Query: " + sql.toString());

        // Establish connection
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set parameters
//            preparedStatement.setString(1, fromStation);
//            preparedStatement.setString(2, toStation);
            
//            if (journeyDate != null && !journeyDate.isEmpty()) {
//                preparedStatement.setString(3, journeyDate);
//            }
            System.out.println("SQL ---: ");

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the results
            out.println("<html><body>");
            out.println("<h2>Train Details</h2>");
            out.println("<table border='1'><tr><th>Train ID</th><th>From Station</th><th>To Station</th><th>Journey Date</th></tr>");

            while (resultSet.next()) {
                System.out.println("SQL result: " + resultSet.toString());

                out.println("<tr>");
                out.println("<td>" + resultSet.getString("TRAIN_ID") + "</td>");
                out.println("<td>" + resultSet.getString("FROM_STATION") + "</td>");
                out.println("<td>" + resultSet.getString("TO_STATION") + "</td>");
                out.println("<td>" + resultSet.getString("JOURNEY_DATE") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");
        }
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<h3>Error: " + e.getMessage() + "</h3>");
    } finally {
        out.close();
    }
}}