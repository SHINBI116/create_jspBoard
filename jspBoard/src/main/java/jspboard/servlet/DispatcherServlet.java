package jspboard.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.process.BoardAddBadProcess;
import jspboard.process.BoardAddGoodProcess;
import jspboard.process.BoardArticleDeleteProcess;
import jspboard.process.BoardArticleModifyFormProcess;
import jspboard.process.BoardArticleModifyProcess;
import jspboard.process.BoardArticleProcess;
import jspboard.process.BoardCommentDeleteProcess;
import jspboard.process.BoardCommentInsertProcess;
import jspboard.process.BoardInsertFormProcess;
import jspboard.process.BoardInsertProcess;
import jspboard.process.BoardListProcess;
import jspboard.process.CheckPasswordProcess;
import jspboard.process.NotfoundProcess;
import jspboard.webprocess.WebProcess;

public class DispatcherServlet extends HttpServlet {
	
	
	final private static HashMap<String, WebProcess> URI_MAPPING
											= new HashMap<>();
	
	final private static String PREFIX = "/WEB-INF/views";
	final private static String SUFFIX = ".jsp";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		URI_MAPPING.put("GET:/notfound", new NotfoundProcess());
		URI_MAPPING.put("GET:/list", new BoardListProcess());
		URI_MAPPING.put("GET:/insert", new BoardInsertFormProcess());
		URI_MAPPING.put("POST:/insert", new BoardInsertProcess());
		URI_MAPPING.put("GET:/article", new BoardArticleProcess());
		URI_MAPPING.put("POST:/comment", new BoardCommentInsertProcess());
		// 글삭제 -> 비번입력 -> 삭제프로세스
		URI_MAPPING.put("GET:/check_delete", new CheckPasswordProcess());
		URI_MAPPING.put("POST:/delete", new BoardArticleDeleteProcess());
		// 댓글삭제
		URI_MAPPING.put("POST:/delete_comment", new BoardCommentDeleteProcess());
		// 수정 -> 비번 입력 -> 수정폼 -> 수정프로세스
		URI_MAPPING.put("GET:/check_modify", new CheckPasswordProcess());
		URI_MAPPING.put("POST:/modify_form", new BoardArticleModifyFormProcess());
		URI_MAPPING.put("POST:/modify", new BoardArticleModifyProcess());
		//추천
		URI_MAPPING.put("GET:/good", new BoardAddGoodProcess());
		//비추천
		URI_MAPPING.put("GET:/bad", new BoardAddBadProcess());

	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath = req.getContextPath();
		
		String method = req.getMethod();
		String uri = req.getRequestURI().substring(contextPath.length());

		System.out.println("요청방식: " + method);
		System.out.println("contextPath가 짤린 요청URI: " + uri);
		

		WebProcess wp = URI_MAPPING.get(method + ":" + uri);
		
		String nextView = null;
		if (wp != null) {
			nextView = wp.process(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/notfound");
			return;
		}
			
		
		if (nextView.startsWith("redirect:")) {
			resp.sendRedirect(nextView.substring("redirect:".length()));
		} else {
			req.getRequestDispatcher(PREFIX + nextView + SUFFIX).forward(req, resp);			
		}
	}
	
}
