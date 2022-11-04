<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=10" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=0, user-scalable=no, target-densitydpi=medium-dpi" />
    <meta name="format-detection" content="telephone=no,address=no,email=no">
    <!-- 캐쉬 삭제  -->
    <meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT">
    <meta http-equiv="Expires" content="-1">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <!-- 캐쉬 삭제  -->
    <title></title>
    <link rel="stylesheet" href="https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css">

</head>
<body style="background-color: #ffffff;">
<style>

</style>
<div id="wrap">
    <div class="login_area">
        <!-- <img src="images/logo.svg" alt="천재교육 ACA 관리자" /> -->
        <h1>닥터매스 관리자 로그인</h1>
        <form id="lForm" name="lForm">
            <div class="login_box">
                아이디: <input type="text" placeholder="아이디" id="user_id" name="user_id"/><br>
                비밀번호: <input class="mt12" type="password" placeholder="비밀번호" id="password" name="password" />
                <div class="btn_right mt15 check_box">
                </div>
                <div class="btn_zone">
                    <a class="btn5 btn_color2" href="javascript:fnLogin();">로그인</a>
                </div>
            </div>
        </form>


        <p class="login_notice">
            <span>* </span>
            <span class="notice_txt">
                    아이디/비밀번호가 기억나지 않을 경우, 담당자에게 문의하시길 바랍니다.<br />
                </span>
        </p>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
    function fnLogin() {
        const userId = document.getElementById("user_id").value;
        const password = document.getElementById("password").value;
        const loginData = {"user_id": userId, "password": password};

        console.log(userId);
        console.log(password);
        console.log(loginData);

        $.ajax({
            type: "POST",
            url: '/admin/login',
            contentType: 'application/json',
            data: JSON.stringify(loginData),
            success: function (result) {
                console.log("response: " + JSON.stringify(loginData));
                //console.log("result: " + result);
                setCookie("user_id",userId,7);
            },
            error: function (jqXHR, status, error) {
                alert("알 수 없는 에러 [ " + error + "]");
            }
        });
    }

    function getCookie(cName) {
        cName = cName + '=';
        var cookieData = document.cookie;
        var start = cookieData.indexOf(cName);
        var cValue = '';
        if(start != -1){
            start += cName.length;
            var end = cookieData.indexOf(';', start);
            if(end == -1)end = cookieData.length;
            cValue = cookieData.substring(start, end);
        }
        return unescape(cValue);
    }

    function setCookie(name,value,expireddays){
        const expiredDate = new Date();
        expiredDate.setDate(expiredDate.getDate() + expireddays);
        document.cookie = name + "=" + escape(value) + "; path=/; expires=" + expiredDate.toGMTString() + ";";
    }

</script>
</html>