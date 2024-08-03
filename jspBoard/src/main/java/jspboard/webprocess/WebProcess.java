package jspboard.webprocess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 웹 프로젝트의 요청은 항상 일정한 절차를 지니게 된다
// 요청 -> 처리 -> 화면그리기 -> 응답

public interface WebProcess {
	// 웹 요청에 대한 처리를 하고 나면 항상 우리는 다음 .jsp의 경로를 알아야 한다
	String process(HttpServletRequest request, HttpServletResponse response);
	
}
