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
import jspboard.dto.JspBoard;
import jspboard.webprocess.WebProcess;

public class BoardListProcess implements WebProcess {

	// 한페이지 당 보이는 게시글 개수
	private static final int PAGE_SIZE = 10;
	
	// 한페이지당 보이는 페이지 수
	private static final int SHOW_PAGE = 10;

	/*
	 * pageNum curr_page start_row end_row count : 총 데이터개수 page_count : 페이지개수
	 * start_page end_page prev_page next_page
	 */

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		int curr_page;
		String page = request.getParameter("page");
		if (page == null) {
			curr_page = 1;
		} else {
			curr_page = Integer.parseInt(page);
		}

		int startRow = (curr_page - 1) * PAGE_SIZE + 1;
		int endRow = curr_page * PAGE_SIZE;
		
		
		ServletContext application = request.getServletContext();
		HikariConnector hikari = (HikariConnector) application.getAttribute("hikari");

		String total_row_sql = "SELECT COUNT(*) AS total FROM jspboard";
		int totalRows = 0;

		String sql = "SELECT * FROM " + "(SELECT rownum rn, board_id, board_title, board_writer, "
				+ "board_password, board_writer_ip_addr, board_content, "
				+ "board_write_date, board_view_count, board_good_count, board_bad_count FROM "
				+ "(SELECT * FROM jspboard ORDER BY board_id DESC)) WHERE rn BETWEEN ? AND ?";

		try (Connection conn = hikari.getConnection();
				PreparedStatement count_psmt = conn.prepareStatement(total_row_sql);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet count_rs = count_psmt.executeQuery();) {

			if (count_rs.next()) {
				totalRows = count_rs.getInt("total");
			}

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			try (ResultSet rs = pstmt.executeQuery();) {
				List<JspBoard> boardList = new ArrayList<>();
				while (rs.next()) {
					JspBoard board = new JspBoard(rs);
					boardList.add(board);
				}
				request.setAttribute("boardList", boardList);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 총 페이지 수 계산
		int total_pages = totalRows % PAGE_SIZE == 0 ? totalRows / PAGE_SIZE : totalRows / PAGE_SIZE + 1;
		
		// 페이지 3개만 보여줌 -> 1 - 3, 4 - 6, 7 - 9, 10 - 12 ...
		int start_page = (curr_page - 1) / SHOW_PAGE *  SHOW_PAGE + 1;
		int end_page = (start_page + SHOW_PAGE - 1) > total_pages ? total_pages : (start_page + SHOW_PAGE - 1);
		request.setAttribute("total_pages", total_pages);
		request.setAttribute("curr_page", curr_page);
		request.setAttribute("start_page", start_page);
		request.setAttribute("end_page", end_page);

		return "/board/list";
	}
}
