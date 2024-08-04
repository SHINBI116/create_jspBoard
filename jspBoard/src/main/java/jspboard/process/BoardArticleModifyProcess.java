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

public class BoardArticleModifyProcess implements WebProcess{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		HikariConnector hikari = (HikariConnector) request.getServletContext().getAttribute("hikari");
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		String sql = "UPDATE jspboard SET board_title = ?, board_content= ? "
				+ "WHERE board_id = ?";
		
		try (
				Connection conn = hikari.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {

				pstmt.setString(1, request.getParameter("board_title"));
				pstmt.setString(2, request.getParameter("board_content"));
				pstmt.setInt(3, board_id);
				
				
				int row = pstmt.executeUpdate();
				
				if (row <= 0) {
					System.out.println("수정실패!");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return "redirect:/article?board_id=" + board_id;
	}

}
