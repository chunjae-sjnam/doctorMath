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
<script>
    function fileDownload(id){
        console.log('fileDownload');
        console.log(id);
    }
</script>
<body>
<h5>HOME > 고객센터 > 공지사항 - 상세</h5>
<h3> 공지사항 - 상세 </h3>
<div>
    <table>
        <tr>
            <td>[공지사항] ${data.list.title} |</td>
            <td>날짜 ${data.list.created_at} </td>
        </tr>
    </table>
    <br><br><br><br>

    <table>
        <tr>
            <td>${data.list.content} </td>
        </tr>
    </table>
    <!--첨부 이미지가 있으면 이미지 보여주고, 이미지가 없으면 공란-->
    <table>

    </table>
    <table>
        <tr>
            <!--첨부파일: <a href="javascript:;" onclick="fileDownload('${data2.attachInfo.board_id}');">${data2.attachInfo.attach_file_name}</a><br>-->
            첨부파일: <a href="${download_path}">${data2.attachInfo.attach_file_name}</a><br>
        </tr>
    </table>
</div>

<div align="center">
    <a href="notice"><h3>목록</h3></a>
</div>
</body>
</html>
