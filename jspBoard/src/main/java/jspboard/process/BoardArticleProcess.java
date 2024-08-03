package jspboard.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dao.HikariConnector;
import jspboard.dto.JspBoard;
import jspboard.webprocess.WebProcess;

public class BoardArticleProcess implements WebProcess{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		System.out.println("글번호: " + board_id);
		
		ServletContext application = request.getServletContext();
		HikariConnector hikari = (HikariConnector) application.getAttribute("hikari");
		
		String sql = "SELECT * FROM jspboard WHERE board_id = ?";
		String view_count_sql = "UPDATE jspboard SET "
				+ "board_view_count = board_view_count + 1 WHERE board_id = ?";
		
		try (
			Connection conn = hikari.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement view_count_pstmt = conn.prepareStatement(view_count_sql);
		) {
			pstmt.setInt(1, board_id);
			view_count_pstmt.setInt(1, board_id);
			
			try (ResultSet rs = pstmt.executeQuery();) {
				rs.next();
				JspBoard board = new JspBoard(rs);
				request.setAttribute("board", board);
			}
			
			view_count_pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "/board/article";
	}

}
