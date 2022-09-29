<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>
    <title>메인화면</title>
</head>
<body>

<%--추후 header.jsp로 뺄 예정--%>
<div class="btn_wrap justify_between">
    temp header
    <a href="#none" class="name"><%=request.getSession().getAttribute("USER_ID") %></a>
    <!--
    <div class="box">
        <select class="select_box">
            <option value="">총괄관리자</option>
            <option value="">수집담당자</option>
            <option value="">문제운영자</option>
            <option value="">문제검수자</option>
        </select>
    </div>
-->
    <a href="/main/logout" class="btn_default btn_gray right">로그아웃</a>
</div>


맞춤 문제지 메인 화면
</body>
</html>
