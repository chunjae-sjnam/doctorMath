<%--
  Created by IntelliJ IDEA.
  User: chansung
  Date: 2022-11-08
  Time: 오전 9:51
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>
    <script src="../../../js/common/common.js"></script>
    <script src="../../../js/login/user-sign-ex.js"></script>
    <title>회원가입 임시</title>
</head>
<body>
    <h3>임시 회원가입</h3>
    <form action="/api/main/user-add" method="post">
        <input type="text" id="userId" name="userId" placeholder="아이디" />
        <input type="password" id="password" name="password" placeholder="비밀번호" />
        <button type="submit">등록</button>
    </form>
</body>
<script>

</script>
</html>

