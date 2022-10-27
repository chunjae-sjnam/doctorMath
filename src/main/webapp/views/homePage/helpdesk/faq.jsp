<%--
  Created by IntelliJ IDEA.
  User: DB400TDA
  Date: 2022-10-14
  Time: 오후 3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>FAQ</title>
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<h1> FAQ </h1>
<form name="searchForm" action="faq" method="POST">
<input type="checkbox" name="chk[]" value="title" > 제목
<input type="checkbox" name="chk[]" value="content" > 내용
<input type="checkbox" name="chk[]" value="title_content" checked> 제목+내용
<input type="text" name="searchText" placeholder="검색어를 입력하시오" value="${data.searchTxt}">
<input type="submit" name="search" value="검색">
</form>
<br><br><br>
<c:forEach var="list" items="${data.list}" varStatus="status">
    <h3>Q. ${list.Title}</h3> <br>
    <h5>A. ${list.Content}</h5><br>
    <br><br>
</c:forEach>
</body>
</html>
