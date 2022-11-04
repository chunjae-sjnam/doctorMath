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
  <title>1:1 문의</title>
  <script type="text/javascript" src="//code.jquery.com/jquery-1.11.3.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js"></script>
  <!-- 카카오톡 채널>1:1 채팅 시작하기 -->
  <script src="//t1.kakaocdn.net/kakao_js_sdk/2.0.0/kakao.min.js" integrity="sha384-PFHeU/4gvSH8kpvhrigAPfZGBDPs372JceJq3jAXce11bVA6rMvGWzvP4fMQuBGL" crossorigin="anonymous"></script>
  <!-- 카카오톡 채널>1:1 채팅 시작하기 -->
<script>
  $( document ).ready(function() {
    console.log( "ready!" );
    //chatCreate();
  });

  function inqWrite(){
    console.log('inqWrite()');
    // 유효성체크
    // 제목, 구본, 이름, 휴대폰번호, 이메일, 인증번호
    /*
    if(_.isEmpty($('#title').val())){
      console.log('제목을 입력하시오.');
    }
    if(_.isEmpty($('#name').val())){
      console.log('이름을 입력하시오.');
    }
    if(_.isEmpty($('email').val())){
      console.log('이메일 주소를 확인해주세요.');
    }
    */
    // action="inquiryWrite"
    console.log('zzz');
    $("#formWrite").submit();
  }

  function authReq(){

      //let phoneNumber = $('#inputPhoneNumber').val();
      let phoneNumber = "010-5105-9047";
      console.log('인증번호 발송 완료!');
      alert('인증번호 발송 완료되었습니다 !!!');

      $.ajax({
        type: "POST",
        url: "/auth/SendAuthMessage",
        data: {
          "phoneNumber" : phoneNumber
        },

        success: function(res){
          console.log(res);
          console.log(JSON.parse(JSON.stringify(res)));
          console.log(_.get(res, 'certNum'))

        }
      })

  }

  function chatChannel(){
    Kakao.Channel.chat({
      channelPublicId: "_reDsb" // 카카오톡 채널 홈 URL에 명시된 id로 설정합니다.
    });
  }


  function selectEmail(ele){
    var $ele = $(ele);
    var $email2 = $('input[name=email2]');

    // '1'인 경우 직접입력
    if($ele.val() == "1"){
      $email2.attr('readonly', false);
      $email2.val('');
    } else {
      $email2.attr('readonly', true);
      $email2.val($ele.val());
    }
  }
</script>

</head>
<body>
<h1> 1:1 문의 </h1>
<div>
  <div>
    1:1 문의
  </div>
  <div align="right">
    <!--<a id="channel-chat-button" href="javascript:chatChannel();" >
      톡상담
    </a>
    -->
    톡상담
  </div>
</div>

<div>
  <style type="text/css">
    .tg  {border-collapse:collapse;border-spacing:0;margin:0px auto;}
    .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
      overflow:hidden;padding:10px 5px;word-break:normal;}
    .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
      font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
    .tg .tg-ih3h{border-color:inherit;position:-webkit-sticky;position:sticky;text-align:center;top:-1px;vertical-align:top;
      will-change:transform}
    .tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}
    .tg .tg-0lax{text-align:left;vertical-align:top}
    .tg-sort-header::-moz-selection{background:0 0}
    .tg-sort-header::selection{background:0 0}.tg-sort-header{cursor:pointer}
    .tg-sort-header:after{content:'';float:right;margin-top:7px;border-width:0 5px 5px;border-style:solid;
      border-color:#404040 transparent;visibility:hidden}
    .tg-sort-header:hover:after{visibility:visible}
    .tg-sort-asc:after,.tg-sort-asc:hover:after,.tg-sort-desc:after{visibility:visible;opacity:.4}
    .tg-sort-desc:after{border-bottom:none;border-width:5px 5px 0}</style>
<form id='formWrite' name='formWrite' method="POST" action="inquiryWrite">
  <table id="tg-5KdkF" class="tg" >
    <tbody>
    <tr>
      <td>제목</td>
      <td><input type="text" name="title" id="title" size="20" ></td>
      <td>구분</td>
      <td>
        <select name="type">
          <option value="all" selected="selected">전체</option>
          <option value="service" selected>서비스 이용</option>
          <option value="free">무료체험</option>
          <option value="charge" >이용 요금</option>
          <option value="etc" >기타</option>
        </select>
      </td>
    </tr>
    <tr>
      <td class="tg-0pky">이름</td>
      <td class="tg-0pky">
        <input type="text" name="title" id="title" >
      </td>
      <td class="tg-0pky">휴대폰 번호</td>
      <td class="tg-0pky">
        <select name="mailType">
          <option value="010" selected="selected">010</option>
          <option value="011" >011</option>
          <option value="016" >016</option>
          <option value="017" >017</option>
          <option value="018" >018</option>
          <option value="019" >019</option>
        </select>
        -
        <input type="text" name="phone2" id="phone2" size="7" maxlength="4"> -
        <input type="text" name="phone3" id="phone3" size="7" maxlength="4" >
        <input type="button" name="authReqButton" id="authReqButton" value="인증번호 요청" onclick="authReq()" >
      </td>
    </tr>
    <tr>
      <td class="tg-0lax">이메일</td>
      <td class="tg-0lax">
        <input name="email1" type="text"> @ <input name="email2" type="text" >
        <select name="select_email" onChange="selectEmail(this)">
          <option value="" selected>선택하세요</option>
          <option value="naver.com">naver.com</option>
          <option value="gmail.com">gmail.com</option>
          <option value="kakao.com">kakao.com</option>
          <option value="yahoo.com">yahoo.com</option>
          <option value="1">직접입력</option>
        </select>
      </td>
      <td class="tg-0lax">인증번호</td>

      <td class="tg-0lax">
        <input type="text" name="authNum" id="authNum" ><!--auth/SendAuthMessage-->
        <input type="button" name="authButton" id="authButton" value="인증번호 확인" onclick="authConfirm()" >
      </td>
    </tr>
    <tr>
      <td colspan="5">
        <textarea cols="150" rows="30"  style="resize: none;"> </textarea>
      </td>
    </tr>
    <tr>
      <td colspan="5" align="center">
        <input type="button" name="inquiryWrite" id="inquiryWrite" value="등록" onclick="inqWrite()" >
        <input type="button" name="inquiryCancel" id="inquiryCancel" value="취소" >
      </td>
    </tr>
    </tbody>
  </table>
</form>
</div>

<div align="center">
  <a href="notice"><h3>목록</h3></a>
</div>
</body>
</html>
