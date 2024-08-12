<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="check_delete" value="/check_delete" />
<c:url var="check_modify" value="/check_modify" />
<c:url var="delete_comment" value="/delete_comment" />
<c:url var="good" value="/good" />
<c:url var="bad" value="/bad" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용</title>
<link rel="stylesheet" href="/resources/css/article.css" />
</head>
<body>

	<ul id="post-details">
		<li class="post-item">제목: ${board.board_title }</li>
		<li class="post-item">작성자: ${board.board_writer }</li>
		<li class="post-item">작성일: ${board.board_write_date }</li>
		<li class="post-item">조회수: ${board.board_view_count }</li>
		<li class="post-item">댓글수: ${total_comment }</li>
		<li class="post-item">추천수: ${board.board_good_count }</li>
		<li class="post-item">글내용: ${board.board_content }</li>
	</ul>

	<div id="post-actions">
		<button class="action-btn"
			onclick="location.href = '${good}?board_id=${board.board_id }';">추천</button>
		<span>${board.board_good_count }</span>
		<button class="action-btn"
			onclick="location.href = '${bad}?board_id=${board.board_id }';">비추천</button>
		<span>${board.board_bad_count }</span>
		<button class="action-btn"
			onclick="location.href = '${check_modify}?board_id=${board.board_id }';">수정</button>
		<button class="action-btn"
			onclick="location.href = '${check_delete}?board_id=${board.board_id }';">글삭제</button>
	</div>

	<div id="comment-section">
		<form action="/comment" method="POST" id="comment-write-form">
			<label for="comment_writer">댓글작성자:</label> <input id="comment_writer"
				type="text" name="comment_writer" /> <br> <label
				for="comment_password">비밀번호:</label> <input id="comment_password"
				type="password" name="comment_password" /> <br> <label
				for="comment_content">댓글:</label>
			<textarea id="comment_content" name="comment_content" cols="30"
				rows="3"></textarea>
			<br> <input type="hidden" name="board_id"
				value="${board.board_id }" /> <input type="hidden"
				name="total_comment" value="${total_comment}" /> <input
				type="submit" value="등록" />
		</form>
	</div>

	<ul id="comment-list">
		<c:forEach var="comment" items="${commentList }">
			<li class="comment-item">
				<p>작성자: ${comment.comment_writer }</p>
				<p>작성일: ${comment.comment_write_date }</p>
				<p>내용: ${comment.comment_content }</p>
				<div class="comment-actions" id="comment-${comment.comment_id}">
					<button class="delete-comments">삭제</button>
					<form class="comments-check" action="${delete_comment}"
						method="POST">
						<input type="password" class="comments-pw" name="comment_password"
							placeholder="비밀번호" /> <input type="hidden" name="board_id"
							value="${comment.board_id }" /> <input type="hidden"
							name="comment_id" value="${comment.comment_id }" />
						<button type="submit" class="submit-btns">확인</button>
						<button class="back-btns" type="button">취소</button>
					</form>
				</div>
			</li>
		</c:forEach>
	</ul>

	<script src="resources/js/article.js"></script>

</body>
</html>