<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 값 받기</title>
</head>
<body>
<%@ include file = "db_connection.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	
	String id  = request.getParameter("id");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String tel1 = request.getParameter("tel1");
	String tel2 = request.getParameter("tel2");
	String tel3 = request.getParameter("tel3");
	String tel = tel1 +"-"+ tel2 +"-"+ tel3;
	String age = request.getParameter("age");
	
	PreparedStatement pstmt = null;
	
	try{
		String sql = "insert into user(u_id,u_pw,u_name,u_tel,u_age) values(?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, password);
		pstmt.setString(3, name);
		pstmt.setString(4, tel);
		pstmt.setString(5, age);
		pstmt.executeUpdate();
	} catch(SQLException ex){
		System.out.println("SQLException : "+ex.getMessage());
	} finally {
		if(pstmt != null){
			pstmt.close();
		}
		if(conn != null){
			conn.close();
		}
	}
%>
<%-- 	<p> 아이디 : <%=id %></p> --%>
<%-- 	<p> 비밀번호 : <%=password %></p> --%>
<%-- 	<p> 이름 : <%=name %></p> --%>
<%-- 	<p> 연락처 : <%=tel1 %>-<%=tel2 %>-<%=tel3 %></p> --%>
<%-- 	<p> 나이 : <%=age %></p> --%>
	<h3>저장완료</h3>
	
</body>
</html>