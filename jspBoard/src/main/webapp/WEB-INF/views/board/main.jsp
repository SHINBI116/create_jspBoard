<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="main" value="/main" scope="request"></c:url>
<c:url var="list" value="/list" scope="request"/>
<c:url var="header_css" value="/resources/css/header.css" scope="request"/>
<c:url var="main_css" value="/resources/css/main_body.css" scope="request"/>

<jsp:include page="frags/main/header.jsp"/>
<jsp:include page="frags/main/main.jsp"/>
<jsp:include page="frags/main/footer.jsp"/>