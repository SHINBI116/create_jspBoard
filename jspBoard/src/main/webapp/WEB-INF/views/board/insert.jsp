<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="insert" value="/insert"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 페이지</title>
</head>
<body>
	
	<form action="${insert}" method="POST">
		<ul type="none">
			<li>글제목: <input type="text" name="board_title"/></li>
			<li>작성자: <input type="text" name="board_writer"/></li>
			<li>비밀번호: <input type="password" name="board_password"/></li>
			<li>글내용: <textarea name="board_content" cols="63" rows="12"></textarea></li>
		</ul>
		
		<div>
			<div><input type="submit" value="등록하기"/></div>
			<div><button onclick="history.back();">작성취소</button></div>
		</div>
	</form>
		
	
	<script>
		
	</script>
	
</body>
</html>