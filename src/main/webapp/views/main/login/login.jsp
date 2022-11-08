<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>
    <script src="../../../js/common/common.js"></script>
    <script src="../../../js/login/login.js"></script>
    <title>사용자 로그인 화면</title>
</head>
<body>
<div id="wrap">

    <div class="login_area">
        <!-- <img src="images/logo.svg" alt="천재교육 ACA 관리자" /> -->

        <h1>닥터매쓰</h1>
        <span>회원 서비스 이용을 위해 로그인 해주세요</span>
        <form id="lForm" name="lForm">
            <div class="login_box">
                <input type="text" placeholder="아이디" id="userId" name="user_id"/>
                <input class="mt12" type="password" placeholder="비밀번호" id="password" name="password" onkeydown="javascript:enterKey();"/>
                <div class="btn_right mt15 check_box">
                    <input type="checkbox" id="box1">
                    <label for="box1">로그인 상태 유지</label>
                </div>
                <div class="btn_zone">
                    <a class="btn5 btn_color2" href="javascript:findId();">아이디 찾기</a>
                </div>
                <div class="btn_zone">
                    <a class="btn5 btn_color2" href="javascript:findPassword();">비밀번호 찾기</a>
                </div>

                <div id="msgArea">

                </div>

                <div class="btn_zone">
                    <div class="btn5 btn_color2" onclick="login();">로그인</div>
                </div>
                <div class="btn_zone">
                    <a class="btn5 btn_color2" onclick="freeTrial();">무료체험 신청</a>
                </div>
                <div class="btn_zone">
                    <a class="btn5 btn_color2" onclick="katalkConsulting();">카카오톡 상담</a>
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

</script>
</html>
