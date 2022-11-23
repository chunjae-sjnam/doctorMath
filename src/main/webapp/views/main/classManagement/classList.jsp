<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>
    <script src="../../../js/class/class.js"></script>
    <title>클래스 관리</title>
</head>
<style>

</style>
<body>
<h3>클래스 관리</h3>
<div class="list_btn">
    <div>
        <form action="/api/class/list" method="post">
            <select name="condition">
                <option value=""<c:if test="${condition eq '' }">selected</c:if>>전체</option>
                <option value="className"<c:if test="${condition eq 'className' }">selected</c:if>>클래스명</option>
                <option value="grade"<c:if test="${condition eq 'grade' }">selected</c:if>>학년</option>
                <option value="teacherName"<c:if test="${condition eq 'teacherName' }">selected</c:if>>담당 선생님</option>
            </select>
            <input type="text" name="keyword" value="${keyword }" placeholder="검색"/>
            <button type="submit">검색</button>
        </form>
    </div>
</div>
<div class="list_area">
    <table id="list_table" border="1">
        <th>클래스명</th>
        <th>대상 학년</th>
        <th>인원</th>
        <th>담당 선생님</th>
        <th>상세 정보</th>
        <c:forEach var="list" items="${classList}" varStatus="status">
            <tr align="center">
                <td>${list.className}</td>
                <td>${list.gradeName}</td>
                <td>
                    <c:if test="${!empty list.studentCount}">${list.studentCount}</c:if>
                    <c:if test="${empty list.studentCount}">0</c:if>
                </td>
                <td>
                    <c:if test="${!empty list.mainTeacherName}">${list.mainTeacherName}</c:if>
                    <c:if test="${empty list.mainTeacherName}">-</c:if>
                </td>
                <td><button class="openBtn">보기</button></td>
            </tr>
        </c:forEach>
    </table>
</div>

<!-- 상세보기 -->
<div id="classModal" class="modal hidden"></div>

<h3>등록</h3>
<div id="insertClass">
    <span>클래스명</span>
    <input id="className_i" name="className_i" type="text" maxlength="50">
    <div>
        <p>담당 선생님 선택</p>
        <input id="mainTeacherName_i" name="mainTeacherName_i" type="text" placeholder="검색">
        <button type="button" onclick="mainTeacherList()">검색</button>
        <button hidden type="button" id="mainTeacherName" name="mainTeacherName"></button>
    </div>
    <div>
        <p>보조 선생님 선택</p>
        <input id="subTeacherName_i" name="subTeacherName_i" type="text" placeholder="검색">
        <button type="button" onclick="subTeacherList()">검색</button>
        <button hidden type="button" id="subTeacherName" name="subTeacherName"></button>
    </div>
    <div>
        <p>대상 학년 선택</p>
    </div>
    <div>
        <button type="button">취소</button>
        <button type="button" onclick="insertClass();">저장</button>
    </div>
</div>

</body>
<script>
    // modal
    document.querySelector(".openBtn").addEventListener("click", open);

</script>
</html>
