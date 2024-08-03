<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<li>댓글수: </li>
		<li>추천수: ${board.board_good_count }</li>
		<li>글내용: ${board.board_content }</li>
	</ul>
	
	<div>
		<div><button>추천</button>${board.board_good_count }</div>
		<div><button>비추천</button>${board.board_bad_count  }</div>
	</div>
	
	<div>
	댓글작성자: <input type="text" />
	비밀번호: <input type="password" />
	댓글: <textarea name="" id="" cols="30" rows="3"></textarea>
	<button>등록</button>
	</div>
	
	<ul type="none">
		<!-- 댓글 목록 -->
		<li></li>
	</ul>
	
</body>
</html>