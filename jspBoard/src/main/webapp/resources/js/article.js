
// 댓글작성자,비번,댓글내용 빈칸x
document.getElementById("comment_write_form").addEventListener('submit', (e) => {
    const comment_writer = document.getElementById("comment_writer");
    const comment_password = document.getElementById("comment_password");
    const comment_content = document.getElementById("comment_content");

    if (!comment_writer.value) {
        alert("댓글 작성자 이름을 작성해주세요");
        e.preventDefault();
		comment_writer.focus();
        return;
    }

    if(!comment_password.value) {
        alert("비밀번호를 입력해주세요");
        e.preventDefault();
		comment_password.focus();
        return;
    }

    if(!comment_content.value) {
        alert("댓글 내용을 입력해주세요");
        e.preventDefault();
		comment_content.focus();
        return;
    }



});

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

