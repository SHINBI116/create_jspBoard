<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="modify" value="/modify"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
	<form id="modify_form" action="${modify}" method="POST">
		<ul type="none">
			<input type="hidden" name="board_id" value="${board.board_id }"/>
			<li>글제목: <input id="board_title" type="text" name="board_title" value="${board.board_title }"/><input type="hidden" value="${board.board_id }"/></li>
			<li>작성자: <input id="board_writer" type="text" name="board_writer" value="${board.board_writer }" readonly/></li>
			<li>비밀번호:
				<input type="password" name="dummy_password" placeholder="●●●●" disabled/>
				<input type="hidden" name="board_password" value="${board.board_password}"/>
			</li>
			<li>글내용: <textarea id="board_content" name="board_content" cols="63" rows="12">
							<c:out value="${board.board_content}" />
					   </textarea>
			</li>
		</ul>
		
		<div>
			<div><input type="submit" value="수정하기"/></div>
			<div><button onclick="history.back();">작성취소</button></div>
		</div>
	</form>
	
	<script src="resources/modify_form.js"></script>
</body>
</html>