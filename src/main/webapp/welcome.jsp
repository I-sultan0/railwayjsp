<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Search Train</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
            background-color: #f5f5f5;
        }
        .form-container {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            background: #ffffff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .form-container h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"],
        input[type="date"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1>Search Train</h1>
        <form action="TrainBox" method="POST">
            <!-- From Station -->
            <div class="form-group">
                <label for="fromStation">From Station:</label>
                <input type="text" id="fromStation" name="fromStation" placeholder="Enter departure station" required>
            </div>

            <!-- To Station -->
            <div class="form-group">
                <label for="toStation">To Station:</label>
                <input type="text" id="toStation" name="toStation" placeholder="Enter destination station" required>
            </div>

            <!-- Journey Date -->
            <div class="form-group">
                <label for="journeyDate">Journey Date:</label>
                <input type="date" id="journeyDate" name="journeyDate" required>
            </div>

            <!-- Submit Button -->
            <button type="submit">Search Train</button>
        </form>
    </div>
</body>
</html>
