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
    <title>공지사항</title>
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.3.js"></script>
</head>
<script>
    $( document ).ready(function() {
        //$('#searchType').prop('checked', true)
/*
        let checkList = $('input[type="checkbox"][name="searchType"]').prop('checked');
        console.log(checkList);

        if (checkList) {
            console.log(checkList, '체크됨');
        } else {
            console.log(checkList, '체크해제됨');
            $('#searchType1').prop('checked', true)
        }


        $('input[type="checkbox"][name="searchType"]').click(function(){
            if($(this).prop('checked')){
                $('input[type="checkbox"][name="searchType"]').prop('checked',false);
                $(this).prop('checked',true);
            }
        });

 */
    });
</script>
<body>

<h5>HOME > 고객센터 > 공지사항</h5>
<h3> 공지사항 </h3>
<form name="searchForm" action="notice" method="POST">
<input type="checkbox" name="searchType" id="searchType1" value="title" checked> 제목
<input type="checkbox" name="searchType" id="searchType2" value="content" > 내용
<input type="checkbox" name="searchType" id="searchType3" value="title_content" > 제목+내용
<input type="text" name="searchText" placeholder="검색어를 입력하시오" value="${data.searchTxt}">
<input type="submit" name="search" value="검색">
</form>
<br><br><br>
<style type="text/css">
    .tg  {border-collapse:collapse;border-spacing:0;margin:0px auto;}
    .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
        overflow:hidden;padding:10px 5px;word-break:normal;}
    .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
        font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
    .tg .tg-ih3h{border-color:inherit;position:-webkit-sticky;position:sticky;text-align:center;top:-1px;vertical-align:top;
        will-change:transform}
    .tg .tg-rz9m{position:-webkit-sticky;position:sticky;text-align:center;top:-1px;vertical-align:top;will-change:transform}
    .tg .tg-0lax{text-align:left;vertical-align:top}
    .tg-sort-header::-moz-selection{background:0 0}
    .tg-sort-header::selection{background:0 0}.tg-sort-header{cursor:pointer}
    .tg-sort-header:after{content:'';float:right;margin-top:7px;border-width:0 5px 5px;border-style:solid;
        border-color:#404040 transparent;visibility:hidden}
    .tg-sort-header:hover:after{visibility:visible}
    .tg-sort-asc:after,.tg-sort-asc:hover:after,.tg-sort-desc:after{visibility:visible;opacity:.4}
    .tg-sort-desc:after{border-bottom:none;border-width:5px 5px 0}</style>
<div align="left">
    총 ${listCount} 개
</div>
<table id="tg-e2J9M" class="tg">
    <thead>
    <tr>
        <th class="tg-ih3h">구분</th>
        <th class="tg-rz9m">번호</th>
        <th class="tg-rz9m">제목</th>
        <th class="tg-rz9m">작성일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${data.list}" varStatus="status">
    <tr>
        <td class="tg-0lax">${list.type}</td>
        <td class="tg-0lax" >${list.id}</td>
        <td class="tg-0lax"><a href="noticeDetail?id=${list.id}">${list.title}</a></td>
        <td class="tg-0lax">${list.created_at}</td>

    </tr>
    </c:forEach>

    </tbody>
</table>
<br><br>
<!-- 페이징 -->
<div class="paging mt20 mb20" align="center">
    <c:if test="${paging.prev}">
        <a href="notice${paging.makeQuery(paging.startPage - 1)}"><</a>
    </c:if>

    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="idx">
        <a href="notice${paging.makeQuery(idx)}">${idx}</a>
    </c:forEach>

    <c:if test="${paging.next && paging.endPage > 0}">
        <a href="notice${paging.makeQuery(paging.endPage + 1)}">></a>
    </c:if>
</div>
</body>
</html>
