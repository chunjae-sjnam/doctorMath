<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>
    <title>사용자 로그인 화면</title>
</head>
<body>
<div id="wrap">

    <div class="login_area">
        <!-- <img src="images/logo.svg" alt="천재교육 ACA 관리자" /> -->

        <h1>사용자 로그인 화면</h1>
        <form id="lForm" name="lForm">
            <div class="login_box">
                <input type="text" placeholder="아이디" id="userId" name="user_id"/>
                <input class="mt12" type="password" placeholder="비밀번호" id="password" name="password" onkeydown="javascript:enterKey();"/>
                <div class="btn_right mt15 check_box">
                    <input type="checkbox" id="box1">
                    <label for="box1">아이디 저장</label>
                </div>
                <div class="btn_zone">
                    <a class="btn5 btn_color2" href="javascript:fnLogin();">로그인</a>
                </div>
                <div class="btn_zone">
                    <a class="btn5 btn_color2" href="javascript:fnLogin();">무료체험 신청</a>
                </div>
                <div class="btn_zone">
                    <a class="btn5 btn_color2" href="javascript:fnLogin();">카카오톡 상담</a>
                </div>
            </div>
        </form>


        <p class="login_notice">
            <span>* </span>
            <span class="notice_txt">
                    문의사항은 대표번호...<br />
<%--                    담당자 : 홍길동 (02-000-000)--%>
                </span>
        </p>
    </div>
</div>
</body>

<script>


    function fnLogin() {
        const userId = document.getElementById("userId").value;
        const password = document.getElementById("password").value;
        const loginData = {"userId": userId, "password": password};
        $.ajax({
            type: "POST",
            url: '/main/getLoginInfo',
            contentType: 'application/json',
            data: JSON.stringify(loginData),
            success: function (result) {
                if(result.login == true){
                    if($('input:checkbox[id="box1"]').is(":checked") == true){
                        setCookie("id",userId,7);
                    }else{
                        setCookie("id","",-1);
                    }
                    window.location.href = "/main/preClass";
                }else{
                    alert("아이디/비밀번호를 다시 한번 입력해주세요.");
                }
            },
            error: function (jqXHR, status, error) {
                alert("알 수 없는 에러 [ " + error + "]");
            }
        });
    }

    document.addEventListener("DOMContentLoaded", function(){
        if(getCookie("id") != ""){
            document.getElementById("user_id").value = getCookie("id");
            $('input:checkbox[id="box1"]').attr("checked", true);
        }else{
            $('input:checkbox[id="box1"]').attr("checked", false);
        }
    });

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

    function enterKey(){
        if(window.event.keyCode == 13){
            fnLogin();
        }
    }
</script>
</html>
