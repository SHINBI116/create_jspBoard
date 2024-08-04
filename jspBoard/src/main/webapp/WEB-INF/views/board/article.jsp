<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="check_delete" value="/check_delete"/>
<c:url var="check_modify" value="/check_modify"/>
<c:url var="delete_comment" value="/delete_comment"/>
<c:url var="good" value="/good"/>
<c:url var="bad" value="/bad"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용</title>
</head>
<body>
	
	<ul type="none">
		<li>제목: ${board.board_title }</li>
		<li>작성자: ${board.board_writer }</li>
		<li>작성일: ${board.board_write_date }</li>
		<li>조회수: ${board.board_view_count }</li>
		<li>댓글수: ${total_comment }</li>
		<li>추천수: ${board.board_good_count }</li>
		<li>글내용: ${board.board_content }</li>
	</ul>
	
	<div>
		<div><button onclick="location.href = '${good}?board_id=${board.board_id }';">추천</button>${board.board_good_count }</div>
		<div><button onclick="location.href = '${bad}?board_id=${board.board_id }';">비추천</button>${board.board_bad_count  }</div>
		<div><button onclick="location.href = '${check_modify}?board_id=${board.board_id }';">수정</button></div>
		<div><button onclick="location.href = '${check_delete}?board_id=${board.board_id }';" >글삭제</button></div>
	</div>
	
	<div>
	<form action="/comment" method="POST">
		댓글작성자: <input type="text" name="comment_writer"/> <br>
		비밀번호: <input type="password" name="comment_password"/> <br>
		댓글: <textarea name="comment_content" cols="30" rows="3"></textarea> <br>
		<input type="hidden" name="board_id" value="${board.board_id }" />
		<input type="hidden" name="total_comment" value="${total_comment}" />
		<input type="submit" value="등록"/>
	</form>
	
	</div>
	
	<ul type="none">
		<!-- 댓글 목록 -->
		<c:forEach var="comment" items="${commentList }">
			<li>
				작성자: ${comment.comment_writer } <br>
				작성일: ${comment.comment_write_date } <br>
				내용 : ${comment.comment_content } <br>
				<div class="each-comment-update" id="comment-${comment.comment_id}">
					<button class="delete-comments">삭제</button>
					<form style="visibility: hidden;" class="comments-check" action="${delete_comment}" method="POST">
						<input type="password" class="comments-pw" name="comment_password" placeholder="비밀번호"/>
						<input type="hidden" name="board_id" value="${comment.board_id }"/>
						<input type="hidden" name="comment_id" value="${comment.comment_id }"/>
						<button type="submit" class="submit-btns">확인</button>
						<button class="back-btns">취소</button>
					</form>
						
						
				</div>
				
			</li>
		</c:forEach>
	</ul>
	
	<script>
	const deleteBtns = document.querySelectorAll(".delete-comments");
	const checkForms = document.querySelectorAll(".comments-check");
	const backBtns = document.querySelectorAll(".back-btns");
	const submitBtns = document.querySelectorAll(".submit-btns");

	// 삭제버튼: 비번입력창 나오게
	deleteBtns.forEach(deleteBtn => {
		deleteBtn.addEventListener('click', (e) => {
		    const commentDiv = e.target.closest('.each-comment-update');
		    const checkForm = commentDiv.querySelector('.comments-check');

		     checkForm.style.visibility = 'visible';
		 });
	});
	// 취소버튼 : 비번입력창 다시 visibility: hidden;
	backBtns.forEach(backBtn => {
		backBtn.addEventListener('click', (e) => {
		    e.preventDefault(); // 폼 제출 방지
		    const commentDiv = e.target.closest('.each-comment-update');
		    const checkForm = commentDiv.querySelector('.comments-check');

		     checkForm.style.visibility = 'hidden';
		 });
	});
	</script>
	
	
</body>
</html>