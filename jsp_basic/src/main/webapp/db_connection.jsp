<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	Connection conn = null;

	String url = "jdbc:mysql://localhost:3306/sql_basic";
	String user = "root";
	String dbPassword = "1234";
	
	Class.forName("org.mariadb.jdbc.Driver");
	conn = DriverManager.getConnection(url, user, dbPassword);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>