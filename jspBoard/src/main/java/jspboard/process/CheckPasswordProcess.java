package jspboard.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.webprocess.WebProcess;

public class CheckPasswordProcess implements WebProcess{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		String uri = request.getRequestURI();
		
		System.out.println("요청하는 uri:" + uri);
		
		System.out.println("board_id: " + request.getParameter("board_id"));
		
		// 요청 uri따라 처리 달라짐
		//request.setAttribute("uri", uri);
		request.setAttribute("board_id", request.getParameter("board_id"));
		if (uri.contains("delete")) {
			return "/board/check_delete";
		} else if (uri.contains("modify")) {
			return "/board/check_modify";
		}
		return "redirect:/notfound";
	}

}
