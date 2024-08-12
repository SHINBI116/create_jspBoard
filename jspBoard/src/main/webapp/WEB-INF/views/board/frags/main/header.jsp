<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판에 오신것을 환영합니다 ~ </title>
<script src="https://kit.fontawesome.com/5ae185438f.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${header_css }" />
<link rel="stylesheet" href="${main_css }" />
</head>
<body>
	
	<div id="nav-container">
		<div id="left-nav">
			<div onclick="location.href=${main};">
				<img src="/resources/image/게시판아이콘.png" alt="게시판아이콘" id="board_icon"/>
			</div>
			<button onclick="location.href = '${list}';">게시물 보러가기</button>
			<button>역대 베스트</button>
			<button>공지사항</button>
		</div>
		
		<div id="right-nav">
			<i id="random-article" class="fa-solid fa-shuffle"></i>
			<button>로그인</button>
		</div>
		
	</div>