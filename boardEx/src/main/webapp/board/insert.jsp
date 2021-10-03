<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<style>
	*{
		margin: 0; padding: 0; text-decoration: none; list-style: none;
	}
	.writeForm {
		margin: auto;
		width: 500px;
		height: 700px;
	}	
	table {
	}
	table tr td input{
		width: 450px;
	}
	
	
</style>
</head>
<body>
	<div class="writeForm">
		<h2>게시글 작성</h2>
		<form action="board-insert-process.do2" name="user" method="post">
		<tabel>
			<tr>
				<td>작성자 : </td>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<td>제목 : </td>
				<td><input type="text" name="title" size="80"></td>
			</tr>
			<tr>
				<td>내용 : </td>
				<td><textarea name="content" rows="20" cols="80"></textarea></td>
			</tr>
		</tabel>
		<input type="submit" value="등록하기">
		<input type="button" value="취소하기" onclick="location.href='board-list.do2'">
	</div>
	</form>
</body>
</html>