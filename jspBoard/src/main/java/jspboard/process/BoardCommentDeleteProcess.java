package jspboard.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dao.HikariConnector;
import jspboard.webprocess.WebProcess;

public class BoardCommentDeleteProcess implements WebProcess {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		int comment_id = Integer.parseInt(request.getParameter("comment_id"));
		HikariConnector hikari = (HikariConnector) request.getServletContext().getAttribute("hikari");
		
		String pw_sql = "SELECT comment_password FROM comments WHERE comment_id = ?";
		String sql = "DELETE FROM comments WHERE comment_id = ?";
		
		try (
			Connection conn = hikari.getConnection();
			PreparedStatement pw_pstmt = conn.prepareStatement(pw_sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pw_pstmt.setInt(1, comment_id);
			
			try (ResultSet rs = pw_pstmt.executeQuery();) {
				rs.next();
				String pw = rs.getString("comment_password");
				if (!request.getParameter("comment_password").equals(pw)) {
					request.setAttribute("comment_delete_failed_msg", "비밀번호가 틀립니다.");
				} else {
					pstmt.setInt(1, comment_id);
					
					int row = pstmt.executeUpdate();
					
					if (row <= 0) {
						System.out.println("삭제실패");
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "redirect:/article?board_id=" + board_id;
	}

}
