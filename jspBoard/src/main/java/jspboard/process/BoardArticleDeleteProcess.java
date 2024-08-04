package jspboard.process;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dao.HikariConnector;
import jspboard.webprocess.WebProcess;

public class BoardArticleDeleteProcess implements WebProcess{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ServletContext application = request.getServletContext();
		HikariConnector hikari = (HikariConnector) application.getAttribute("hikari");
		
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		String sql = "DELETE FROM jspboard WHERE board_id = ?";
		
		try (
			Connection conn = hikari.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, board_id);
			
			int row = pstmt.executeUpdate();
			
			if (row <= 0) {
				return "redirect:/article?board_id=" + board_id;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "redirect:/list";
	}

}
