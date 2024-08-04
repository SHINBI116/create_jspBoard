package jspboard.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dao.HikariConnector;
import jspboard.dto.JspBoard;
import jspboard.webprocess.WebProcess;

public class BoardArticleModifyFormProcess implements WebProcess{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// 가져올것 원본글 정보
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		HikariConnector hikari = (HikariConnector) request.getServletContext().getAttribute("hikari");
	    JspBoard board = null;
		String sql = "SELECT * FROM jspboard WHERE board_id = ?";
		
		try (
			Connection conn = hikari.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, board_id);
			
			try (ResultSet rs = pstmt.executeQuery();) {
				rs.next();
				board = new JspBoard(rs);
				
				request.setAttribute("board", board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "/board/modify";
		
		//return "redirect:/article?board_id=" + board_id;
	}

}
