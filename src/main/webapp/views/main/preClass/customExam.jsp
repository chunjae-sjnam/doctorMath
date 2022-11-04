<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%--    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>--%>
<%--    --%>
    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>
    <script>
        $('#selectAreaGrade').click(function (){
            getGrade();
        });
        function showlog(){
            //getGrade();
        }
    </script>
    <title>메인화면</title>
</head>
<body>

<%--추후 header.jsp로 뺄 예정--%>
<div class="btn_wrap justify_between">
    temp header
    <jsp:include page="/views/common/mainHeader.jsp"></jsp:include>

</div>

<br>
<a href="/main/examDownloadFile2" class="btn_default btn_gray right">png로바꾸기</a>
<br>

<span>문제지 보관함</span><span>즐겨찾는 문제지</span>

<div id="selectAreaGrade" ></div>

<div style="border: bisque; text-align: center">
맞춤 문제지 메인 화면
    <%-- <tl:testtaglin/> --%>
<div id="listResult"></div>
</div>
</body>
</html>
