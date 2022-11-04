<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <title>Title</title>
    <style>
        /*#tab1 {*/
        /*    display: block;*/
        /*}*/

        .tabview {
            display: none;
        }

        .tabview:target {
            display: block;
        }

        [data-columns] {
            display: grid;
            grid-gap: 1rem;
            padding: 1rem;
            margin: 0 0 1rem 0;
        }

        [data-columns] > div {
            /*height: 100px;*/
            background: white;
        }

        [data-columns="2"] {
            background: #64B5F6;
            grid-template-columns: repeat(2, 1fr);
        }
        .templateListArea{
            display: block;
            border: 1px solid red;
        }
.newTextContent{
    display: block;
    border: 1px solid red;
}



    </style>

    <script>

        function fnShowDetail(content){
            $('.smsDetail').text(content)
        }

        $("#testClose").click(function (){
            $(".dialog-template-view").dialog("close");
        });

        $(".testClose").click(function (){
            $(".dialog-template-view").dialog("close");
        });

    </script>
</head>
<body>

<section>
    <a class="tab" href="#tabTemplate1">템플릿 선택</a>
    <a class="tab" href="#tabTemplate2">템플릿 추가</a>
</section>

<section>
<%--  템플릿 선택  --%>
    <div id="tabTemplate1" class="tabview">
        <div data-columns="2">
<%--            검색 및 템플릿 리스트--%>
    <div class="templateListArea">
    <input type="search" class="searchArea" placeholder="검색 ">

<c:forEach var="template" items="${data}">
    <div class="singleLine">
        <span onclick="fnShowDetail('${template.content}')">${template.title}</span>
    </div>
</c:forEach>

    </div>


<%--    템플릿 상세 내용--%>
            <div class="smsDetail" contenteditable="true"></div>
        </div>
        <button class="textClose" >취소</button>
        <button class="textSelect" >선택</button>
    </div>

<%--   템플릿 추가 --%>
    <div id="tabTemplate2" class="tabview">
        <input type="text" id="newTitle" name="newTitle" placeholder="제목(최대 15자 이내)">
        <div class="newTextContent" contenteditable="true">

        </div>
        <button class="textClose" >취소</button>
        <button class="textUpd" >선택</button>
    </div>
</section>


</body>

</html>
