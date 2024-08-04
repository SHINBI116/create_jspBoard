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
	
	
		<ul type="none">
			<li>글제목: <input id="board_title" type="text" name="board_title" form="insert_form"/></li>
			<li>작성자: <input id="board_writer" type="text" name="board_writer" form="insert_form"/></li>
			<li>비밀번호: <input id="board_password" type="password" name="board_password" form="insert_form"/></li>
			<li>글내용: <textarea id="board_content" name="board_content" cols="63" rows="12" form="insert_form"></textarea></li>
		</ul>
		
		<div>
			<div><input type="submit" value="등록하기" form="insert_form"/></div>
			<div><button onclick="history.back();">작성취소</button></div>
		</div>
	<form id="insert_form" action="${insert}" method="POST"></form>
		
	
	<script src="resources/insert.js"></script>
	
</body>
</html>