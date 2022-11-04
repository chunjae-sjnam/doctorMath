<%--
  Created by IntelliJ IDEA.
  User: DB400TDA
  Date: 2022-11-01
  Time: 오후 4:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>사용후기</title>

</head>

<h5>HOME > 고객센터 > 사용후기</h5>
<h3> 사용후기 </h3>
    <c:forEach var="list" items="${data.list}" varStatus="status">
        <div style="float:left;width:50%;height:50%;background-Color:#F2FFFF">
            <a href="boardDetail?id=${list.id}">${list.title}</a>
        </div>
    </c:forEach>


<!-- 페이징 -->
<div class="paging mt20 mb20" align="center">
    <c:if test="${paging.prev}">
        <a href="review${paging.makeQuery(paging.startPage - 1)}"><</a>
    </c:if>

    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="idx">
        <a href="review${paging.makeQuery(idx)}">${idx}</a>
    </c:forEach>

    <c:if test="${paging.next && paging.endPage > 0}">
        <a href="review${paging.makeQuery(paging.endPage + 1)}">></a>
    </c:if>
</div>


</body>
</html>
