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
    <title>공지사항 - 상세</title>
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<h1> 공지사항 - 상세 </h1>
<div>
    <table>
        <tr>
            <td>[공지사항] ${data.list.Title} |</td>
            <td>날짜 ${data.list.CreDate} </td>
        </tr>
    </table>
    <br><br><br><br>

    <table>
        <tr>
            <td>${data.list.Content} </td>
        </tr>
    </table>

    <table>
        <tr>
            첨부파일:
        </tr>
    </table>
</div>

<div align="center">
    <a href="notice"><h3>목록</h3></a>
</div>
</body>
</html>
