package jspboard.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jspboard.dao.HikariConnector;
import jspboard.webprocess.WebProcess;

public abstract class EvaluationProcess implements WebProcess {

    protected abstract String getSql(); // 추천/비추천 쿼리
    protected abstract String getCookieNamePrefix(); // good_voted_ / bad_voted_

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        int board_id = Integer.parseInt(request.getParameter("board_id"));
        String cookie_name = getCookieNamePrefix() + board_id;
        boolean alreadyVoted = checkAlreadyVoted(request, cookie_name);

        if (!alreadyVoted) {
            upEvaluation(request, response, board_id, getSql(), cookie_name);
        } else {
            request.setAttribute("message", getMessage());
        }

        return "redirect:/article?board_id=" + board_id;
    }

    protected boolean checkAlreadyVoted(HttpServletRequest request, String cookie_name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookie_name)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // 추천비추천증가
    protected void upEvaluation(HttpServletRequest request, HttpServletResponse response, 
                                int board_id, String sql, String cookie_name) {
        HikariConnector hikari = (HikariConnector) request.getServletContext().getAttribute("hikari");
        try (
        		Connection conn = hikari.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            Cookie evaluationCookie = new Cookie(cookie_name, "true");
            evaluationCookie.setMaxAge(24 * 60 * 60); // 1일 1회
            response.addCookie(evaluationCookie);
            
            pstmt.setInt(1, board_id);
            int row = pstmt.executeUpdate();
            if (row > 0) {
            	System.out.println("평가 증가!");
            }
           

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 추천/비추천 1일 1회
    protected abstract String getMessage();
}
