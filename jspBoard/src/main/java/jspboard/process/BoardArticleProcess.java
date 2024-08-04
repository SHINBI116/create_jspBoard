package jspboard.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dao.HikariConnector;
import jspboard.dto.Comments;
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
		// 조회수
		String view_count_sql = "UPDATE jspboard SET "
				+ "board_view_count = board_view_count + 1 WHERE board_id = ?";
		// 댓글목록
		String comment_list_sql = "SELECT * FROM comments WHERE board_id = ?";
		//댓글수
		String total_comment_sql = "SELECT COUNT(*) AS total_comment FROM comments WHERE board_id = ?";
		try (
			Connection conn = hikari.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmt2 = conn.prepareStatement(total_comment_sql);
			PreparedStatement view_count_pstmt = conn.prepareStatement(view_count_sql);
			PreparedStatement comment_list_pstmt = conn.prepareStatement(comment_list_sql);
		) {
			pstmt.setInt(1, board_id);
			pstmt2.setInt(1, board_id);
			view_count_pstmt.setInt(1, board_id);
			comment_list_pstmt.setInt(1, board_id);
			
			Integer total_comment;
			try (ResultSet rs = pstmt2.executeQuery();) {
				rs.next();
				total_comment = rs.getInt("total_comment");
			}
			request.setAttribute("total_comment", total_comment);
			
			try (ResultSet rs = pstmt.executeQuery();) {
				rs.next();
				JspBoard board = new JspBoard(rs);
				request.setAttribute("board", board);
			}
			
			// 조회수 추가
			view_count_pstmt.executeUpdate();
			
			try (ResultSet rs = comment_list_pstmt.executeQuery();) {
				List<Comments> commentList = new ArrayList<>();
				while(rs.next()) {
					Comments comment = new Comments(rs);
					commentList.add(comment);
				}
				request.setAttribute("commentList", commentList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "/board/article";
	}

}
