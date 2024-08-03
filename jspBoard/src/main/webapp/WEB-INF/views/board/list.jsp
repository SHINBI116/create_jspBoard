<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="insert" value="/insert"/>
<c:url var="article" value="/article"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 목록</title>
</head>
<body>
	<h3>게시판입니다</h3>
	<button onclick="location.href='${insert}';">글쓰기</button>

	<table border="1">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
		<c:forEach var="board" items="${requestScope.boardList}">
			<c:set var="board_id" value="${board.board_id}"/>
			<tr onclick="board_article(${board_id})">
				<td>${board_id}</td>
				<td>${board.board_title}</td>
				<td>${board.board_writer}</td>
				<td>${board.board_write_date}</td>
				<td>${board.board_view_count}</td>
				<td>${board.board_good_count}</td>
			</tr>
		</c:forEach>
	</table>
	
	<script>
	const board_article = board_id => {
		const article = `${article}`;
		console.log("board_id", board_id);
		location.href = article + '?board_id=' + board_id;
	}
	</script>
	
</body>
</html>