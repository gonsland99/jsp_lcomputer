<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 필요</title>
</head>
<body>
	<h1>로그인이 필요합니다</h1>
	<h3>3초 후 로그인 페이지로 다시이동...</h3>
	<script>
		setTimeout(function (){
			window.location.href = "user-login.do";
		},3000);
	</script>
</body>
</html>