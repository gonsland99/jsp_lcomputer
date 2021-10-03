<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<style>
	h1{ text-align: center;}
	table{ 
		border-collapse:collapse;
		margin: 40px auto;
	}
	table tr th{font-weight:700;}
	table tr td, table tr th{
		border: 1px solid #818181;
		width: 200px;
		text-align: center;
	}
	a{
		text-decoration: none;
		color: blue;
		font-weight: 700;
	}
	ul{
		width: 600px;
		height: 50px;
		margin: 10px auto;
	}
	li{
		list-style: none;
		width: 50px;
		line-height: 50px;
		border: 1px solid #ededed;
		float: left;
		text-align: center;
		margin: 0 5px;
		border-radius 5px;
	}
</style>
<body>
	<table>
		<tr>
<!-- 			<th>rownum</th> -->
			<th>No</th>
			<th>제목</th>
			<th>내용</th>
			<th>작성자</th>
			<th>작성일자</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${bList}" var="item" varStatus="status">
			<tr>
				<td><a href="board-detail.do2?page=${item.b_idx}">${item.b_rownum}</a></td>
<%-- 				<td>${item.b_idx}</td> --%>
				<td>${item.b_title}</td>
				<td>${item.b_content}</td>
				<td>${item.b_writer}</td>
				<td>${item.b_date}</td>
				<td>13</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<!-- 	pagination -->
	<div>
		<ul>
			<c:choose>
				<c:when test="${pagination.endPage gt 5 }">
					<li>
						<a href="board-list.do2?page=${pagination.prevPage }">
							◀
						</a>
					</li>
				</c:when>
			</c:choose>
			<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
				<c:choose>
					<c:when test="${pagination.page eq i }">
						<li style="background-color:#ededed;">
							<span>${i}</span>
						</li>
					</c:when>
					<c:when test="${pagination.page ne i }">
						<li>
							<a href="board-list.do2?page=${i}">${i}</a>
						</li>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pagination.nextPage le pagination.lastPage }">
					<li>
						<a href="board-list.do2?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose>
		</ul>
	</div>
</body>
</html>