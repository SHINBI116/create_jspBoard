package jspboard.process;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dao.HikariConnector;
import jspboard.webprocess.WebProcess;

public class BoardInsertProcess implements WebProcess {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ServletContext application = request.getServletContext();
		HikariConnector hikari = (HikariConnector) application.getAttribute("hikari");

		String sql = "INSERT INTO " + "jspboard(board_id, " + "board_title, " + "board_writer, " + "board_password, "
				+ "board_writer_ip_addr, " + "board_content) " + "values(board_sequence.NEXTVAL, ?, ?, ?, ?, ?)";

		// System.out.println("ip주소: " + request.getHeader("X-Forwarded-For"));

		try (Connection conn = hikari.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			String ipAddr = getClientIpAddress(request);
//			String shortIpAddr = getBoardIpAddr(ipAddr);
			
//			System.out.println("ip :" + ipAddr);
//			System.out.println("출력되는 ip: " + shortIpAddr);
			
			pstmt.setString(1, request.getParameter("board_title"));
			pstmt.setString(2, request.getParameter("board_writer"));
			pstmt.setString(3, request.getParameter("board_password"));
			pstmt.setString(4, ipAddr);
			pstmt.setString(5, request.getParameter("board_content"));

			int row = pstmt.executeUpdate();

			System.out.printf("게시글 %d개 추가완료\n", row);
			if (row <= 0) {
				return "redirect:/insert";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "redirect:/list";
	}

	private String getClientIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.isEmpty()) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
//	아이피 두자리만 보이게
//	private String getBoardIpAddr(String ip_addr) {
//		Pattern p = Pattern.compile("((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])[.](25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9]))");
//		
//		Matcher m = p.matcher(ip_addr);
//		
//		if (m.find()) {
//            return m.group(0);
//        }
//		
//		return null;
//	}
//	

}
