<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<div align=center>
	<h1>User login</h1>

<form action=LoginServlet method="POST">
<table>
<tr><td>Enter Name: </td><td><input type=text name=username></td></tr>
<tr><td>Enter Password: </td><td><input type=password name=password></td></tr>
<tr><td><input type=submit value=login></td><td><input type=reset></td></tr>
</table>
</form>
</div>
</body>
</html>