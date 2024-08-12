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
import jspboard.webprocess.Pazing;
import jspboard.webprocess.PazingImpl;
import jspboard.webprocess.WebProcess;

public class BoardListProcess implements WebProcess {


    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
    	Pazing paging = new PazingImpl();
        ServletContext application = request.getServletContext();
        HikariConnector hikari = (HikariConnector) application.getAttribute("hikari");

        String totalRowSql = "SELECT COUNT(*) AS total FROM jspboard";
        int totalRows = 0;

        try (Connection conn = hikari.getConnection();
             PreparedStatement countPsmt = conn.prepareStatement(totalRowSql);
             ResultSet countRs = countPsmt.executeQuery()) {

            if (countRs.next()) {
                totalRows = countRs.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        paging.calculatePaging(totalRows, request);

        String sql = "SELECT * FROM (SELECT rownum rn, board_id, board_title, board_writer, "
                + "board_password, board_writer_ip_addr, board_content, "
                + "board_write_date, board_view_count, board_good_count, board_bad_count FROM "
                + "(SELECT * FROM jspboard ORDER BY board_id DESC)) WHERE rn BETWEEN ? AND ?";

        try (Connection conn = hikari.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, paging.getStartRow());
            pstmt.setInt(2, paging.getEndRow());
            try (ResultSet rs = pstmt.executeQuery()) {
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

        request.setAttribute("total_pages", paging.getTotalPages());
        request.setAttribute("curr_page", paging.getCurrPage());
        request.setAttribute("start_page", paging.getStartPage());
        request.setAttribute("end_page", paging.getEndPage());

        return "/board/list";
    }
}
