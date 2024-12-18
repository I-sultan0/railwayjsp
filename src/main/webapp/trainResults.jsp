<!DOCTYPE html>
<html>
<head>
    <title>Train Search Results</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Train Search Results</h2>

    <table>
        <thead>
            <tr>
                <th>Train ID</th>
                <th>Train Name</th>
                <th>From Station</th>
                <th>To Station</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="train" items="${trainList}">
                <tr>
                    <td>${train.trainId}</td>
                    <td>${train.trainName}</td>
                    <td>${train.fromStation}</td>
                    <td>${train.toStation}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
