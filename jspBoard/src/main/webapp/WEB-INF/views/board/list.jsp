<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="insert" value="/insert" />
<c:url var="article" value="/article" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 목록</title>
<link rel="stylesheet" href="/resources/css/list.css" />
</head>
<body>
    <h3>게시판입니다</h3>
    <button onclick="location.href='${insert}';">글쓰기</button>

    <table border="1">
        <tr>
            <th>글번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
            <th>추천수</th>
        </tr>
        <c:forEach var="board" items="${boardList}">
            <c:set var="board_id" value="${board.board_id}" />
            <tr onclick="board_article(${board_id})">
                <td>${board_id}</td>
                <td>${board.board_title}</td>
                <td>${board.board_writer}</td>
                <td>${board.board_write_date}</td>
                <td>${board.board_view_count}</td>
                <td>${board.board_good_count}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="6">
                <!-- 페이징 구현 -->
                <c:if test="${curr_page != 1}">
                    <a href="?page=1">최신 페이지로</a>
                </c:if>
                <c:if test="${curr_page > 1}">
                    <a href="?page=${curr_page - 1}">이전</a>
                </c:if>
                <c:forEach begin="${start_page}" end="${end_page}" var="page">
                    <c:choose>
                        <c:when test="${page == curr_page}">
                            <strong>[${page}]</strong>
                        </c:when>
                        <c:otherwise>
                            <a href="?page=${page}">[${page}]</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${curr_page < total_pages}">
                    <a href="?page=${curr_page + 1}">다음</a>
                </c:if>
                <c:if test="${curr_page != total_pages}">
                    <a href="?page=${total_pages}">마지막 페이지로</a>
                </c:if>
            </td>
        </tr>
    </table>

    <script>
    const board_article = board_id => {
        const article = `${article}`;
        console.log("board_id", board_id);
        location.href = article + '?board_id=' + board_id;
    }
    </script>

</body>
</html>
