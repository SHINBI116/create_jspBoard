package jspboard.process;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dao.HikariConnector;
import jspboard.webprocess.WebProcess;

public class BoardCommentInsertProcess implements WebProcess{
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		ServletContext application = request.getServletContext();
		HikariConnector hikari = (HikariConnector) application.getAttribute("hikari");
		
		String sql = "INSERT INTO comments("
				+ "board_id, "
				+ "comment_id, "
				+ "comment_content, "
				+ "comment_writer, "
				+ "comment_password"
				+ ")"
				+ " VALUES(?, comment_sequence.nextVal, ?, ?, ?)";
	
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		try (
			Connection conn = hikari.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			int total_comment = Integer.parseInt(request.getParameter("total_comment"));
			pstmt.setInt(1, board_id);
			pstmt.setString(2, request.getParameter("comment_content"));
			pstmt.setString(3, request.getParameter("comment_writer"));
			pstmt.setString(4, request.getParameter("comment_password"));
			
			int row = pstmt.executeUpdate();
			System.out.printf("%d번글에 댓글 %d개 추가\n", board_id, row);
			if (row <= 0) {
				return "redirect:/notfound";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/article?board_id=" + board_id;
	}

}
