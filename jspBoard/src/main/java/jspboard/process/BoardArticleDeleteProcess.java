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
		String pw_sql = "SELECT board_password FROM jspboard WHERE board_id = ?";
		try (
			Connection conn = hikari.getConnection();
			PreparedStatement pw_pstmt = conn.prepareStatement(pw_sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pw_pstmt.setInt(1, board_id);
			
			try (ResultSet rs = pw_pstmt.executeQuery();) {
				rs.next();
				String pw = rs.getString("board_password");
				String input_pw = request.getParameter("board_password");
				
				if (!pw.equals(input_pw)) {
					request.setAttribute("error_msg", "비밀번호가 틀립니다");
					return "redirect:/article?board_id=" + board_id;
				} else {
					pstmt.setInt(1, board_id);
					
					pstmt.executeUpdate();
					
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "redirect:/list";
	}

}
