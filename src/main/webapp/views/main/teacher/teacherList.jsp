<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>
    <script src="../../../js/common/common.js"></script>
    <script src="../../../js/teacher/teacher.js"></script>
    <title>선생님 관리</title>
</head>
<style>
    .modal {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .modal .bg {
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.6);
    }

    .modalBox {
        position: absolute;
        background-color: #fff;
        width: 400px;
        height: 200px;
        padding: 15px;
    }

    .modalBox button {
        display: block;
        width: 80px;
        margin: 0 auto;
    }

    .hidden {
        display: none;
    }
</style>
<body>
    <h1>선생님 관리</h1>
    <h3>목록</h3>
    <table border="1">
        <th>이름</th>
        <th>아이디</th>
        <th>연락처</th>
        <th>상세정보</th>
        <c:forEach var="list" items="${teacherList}">
            <tr>
                <td>${list.name}</td>
                <td>${list.userId}</td>
                <td>${list.tell}</td>
                <td><button class="openBtn" onclick="teacherDetail('${list.teacherCode}');">상세보기</button></td>
            </tr>
        </c:forEach>
    </table>

<!-- 상세보기 -->
<div id="teacherModal" class="modal hidden"></div>

<h3>등록</h3>
<div id="insertTeacher">
    <table border="1">
        <tr>
            <th>이름</th>
            <td><input id="name_i" name="name_i" type="text"></td>
        </tr>
        <tr>
            <th>아이디</th>
            <td><input id="userId_i" name="userId_i" type="text"></td>
        </tr>
        <tr>
            <th>연락처</th>
            <td><input id="tell_i" name="tell_i" type="text"></td>
        </tr>
    </table>
    <button onclick="insertTeacher()" >등록</button>
</div>


</body>
<script>
    // modal
    document.querySelector(".openBtn").addEventListener("click", open);

</script>
</html>
