<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<div>
		<p>비밀번호를 입력해주세요!</p>
		<form action="${modify_form}" method="POST">
			<input type="hidden" name="board_id" value="${board_id }" /> <input
				type="password" name="board_password" required />
			<div>
				<button type="submit">확인</button>
				<button type="button" onclick="history.back();">취소</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>