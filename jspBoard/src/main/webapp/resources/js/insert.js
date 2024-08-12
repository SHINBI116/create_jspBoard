document.getElementById("insert_form").addEventListener('submit', (e) => {
	const board_title = document.getElementById("board_title");
	const board_writer = document.getElementById("board_writer");
	const board_password = document.getElementById("board_password");
	const board_content = document.getElementById("board_content");
	
	if(!board_title.value) {
		alert("글 제목을 입력해주세요");
		e.preventDefault();
		board_title.focus();
		return;
	}
	
	if(!board_writer.value) {
		alert("작성자명을 입력해주세요");
		e.preventDefault();
		board_writer.focus();
		return;
	}
	
	if(!board_password.value) {
		alert("비밀번호를 입력해주세요");
		e.preventDefault();
		board_password.focus();
		return;
	}
	
	if(!board_content.value){
		alert("글내용을 입력해주세요");
		e.preventDefault();
		board_content.focus();
		return;
	}
});