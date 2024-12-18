<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Fetch user details from request attributes or parameters
    String username = request.getParameter("username");
    String email = request.getParameter("email");

    // Validate that the required parameters are present
    if (username == null || email == null || username.isEmpty() || email.isEmpty()) {
%>
        <h3>Error: Missing user details!</h3>
        <a href="login.jsp">Go back to Login</a>
<%
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .details {
            margin: 20px 0;
            line-height: 1.8;
        }
        .details label {
            font-weight: bold;
            color: #555;
        }
        .buttons {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .btn {
            padding: 10px 20px;
            font-size: 16px;
            background: #007BFF;
            color: #fff;
            text-decoration: none;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin: 0 10px;
            text-align: center;
        }
        .btn:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome, <%= username %>!</h1>
        <div class="details">
            <p><label>Username:</label> <%= username %></p>
            <p><label>Email:</label> <%= email %></p>
        </div>
        <div class="buttons">
            <form action="logout.jsp" method="post">
                <button type="submit" class="btn">Logout</button>
            </form>
        </div>
    </div>
</body>
</html>
