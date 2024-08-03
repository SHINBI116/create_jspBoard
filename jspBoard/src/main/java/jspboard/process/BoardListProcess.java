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

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {

        // ServletContext를 통해 HikariConnector 가져오기
        ServletContext application = request.getServletContext();
        HikariConnector hikari = (HikariConnector) application.getAttribute("hikari");
        
        String sql = "SELECT * FROM jspboard";
        
        try (
        	Connection conn = hikari.getConnection();
        	PreparedStatement pstmt = conn.prepareStatement(sql);
        	ResultSet rs = pstmt.executeQuery();
        ) {
        	List<JspBoard> boardList = new ArrayList<>();
        	while(rs.next()) {
        		JspBoard board = new JspBoard(rs);
        		boardList.add(board);
        	}
        	
        	request.setAttribute("boardList", boardList);
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "/board/list";
    }
}
