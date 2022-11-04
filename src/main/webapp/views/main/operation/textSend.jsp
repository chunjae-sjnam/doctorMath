<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%--    <script type="text/javascript" src="../../../js/jquery.js" crossorigin="anonymous"></script>--%>
<%--    <script type="text/javascript" src="../../../js/jquery-ui.js"></script>--%>
<%--    <script type="text/javascript" src="../../../css/jquery-ui.css"></script>--%>

<%--    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>--%>



    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script type="text/javascript" src="../../../js/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<%--    <link rel="stylesheet" href="../../../css/jquery-ui.css">--%>



<%--    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>--%>
    <title>Title</title>

    <style>
        #tab1 {
            display: block;
        }

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

        .dataArea {
            height: available;
            background: #dde8f1;
        }

        .testLabel {
            background: #f1b200;
        }
        .sms-history{
            height: 700px;
            background-color: coral;
        }
        .ext-sms-his{
            background-color: cornsilk;
        }


    </style>

    <style>
        .open-dialog-all .tooltip {transform:translateY(-100%);z-index: 3;}
        .open-dialog-all:hover .tooltip {display:block}
    </style>

    <script>


        function fnShowContent(contentValue) {
            document.getElementById("smsEdite").value = contentValue;
        }

        function fnAllClassification(popupNo) {

            $('#showOpen_' + popupNo).css('display','none');
            $('#showSimpl_' + popupNo).css('display','block');
            let params = new URLSearchParams();
            // params.set('chapter_group',chapterGroup);
            // params.set('version',popupNo);
            // params.set('curri',curriVal);
            // params.set('grade',gradeVal);
            // params.set('search_txt',searchTxt);
            $.ajax({
                type: "POST",
                url: '/view/getTreeClassList',
                cache: false,
                success: function (data) {


                    $('#MoaModal .modal-content').load("moaModal?id="+data);
                    $('#MoaModal').modal();
                },
                error: function (jqXHR, status, error) {
                    alert('에러');
                }
            });
        }

        function fnModuleInfo(str){
            $('#MoaModal .modal-content').load("moaModal?id="+str);
            $('#MoaModal').modal();

        }


        $(function () {
            // $( ".dialog-all-view" ).load("/main/getTreeClassList").dialog('open');

            $(".dialog-all-view").dialog({
                autoOpen: false,
                dialogClass: "basic-type",
                width: 1060,
                modal: true,
                resizable: true,
                open: function (event, ui) {

                },
                buttons:{
                    "취소":function (){
                        $(this).dialog('close');
                    },
                    "확인":function (){
                        console.log($(".nameSpaceClass"));
                        let html='';
                        for(let i=0; i<$(".nameSpaceClass").length; i++ ){
                            html += '<input type="button" class="nameSendSpaceClass" id="'+$(".nameSpaceClass")[i].id+'" name="keyVal" value="'+$(".nameSpaceClass")[i].value+'" readonly="true" onclick="fnRemove(this)">';

                        }
                        $('.dataSendingArea').append(html);

                        $(this).dialog("close");
                    },
                }
            });

            $(".dialog-template-view").dialog({
                autoOpen: false,
                dialogClass: "basic-type",
                width: 1060,
                modal: true,
                resizable: true,

                open:function(event, ui){
                    // $.getScript('../../../js/searchFz.js');
            },

           // } ,
                buttons:{
                    "취소":function (){
                        $(this).dialog('close');
                    },
                    "확인":function (){
                        console.log($(".nameSpaceClass"));
                        let html='';
                        for(let i=0; i<$(".nameSpaceClass").length; i++ ){
                            html += '<input type="button" class="nameSendSpaceClass" id="'+$(".nameSpaceClass")[i].id+'" name="keyVal" value="'+$(".nameSpaceClass")[i].value+'" readonly="true" onclick="fnRemove(this)">';

                        }
                        $('.dataSendingArea').append(html);

                        $(this).dialog("close");
                    },
                }
            });

        });

        function fnRemove(e){

            $(e).remove();

        }


        function test(){
            const param = {
                makerId : 'makerId',
                makerType : 'makerType',
                popup: true
            }
            //popupWindow('/view/getTreeClassList', "수업하기", param, "width=" + '50' + ", height=" + screen.height);
            $( ".dialog-all-view" ).load("/main/getTreeClassList").dialog('open');
            // $( ".dialog-all-view" ).load("/main/getTreeClassList").dialog( {
            //     autoOpen: true,
            //     dialogClass: "basic-type",
            //     width: 1060,
            //     modal: true,
            //     resizable: true
            // } );


        }

        function test2(){
            $( ".dialog-template-view" ).load("/main/getTemplateList").dialog('open');
        }



        function popupWindow(url, alt, param, spec) {
            alt = alt | "_blank";
            //
            param.popup = true;
            window.open(url + paramToQueryString(param), alt, spec);
        }

        function paramToQueryString(param) {
            let queryString = "?";
            if (param) {
                const keys = Object.keys(param);
                for (let i = 0; i < keys.length; i++) {
                    let key = keys[i];
                    queryString += (key + "=" + param[key]);
                    if (i < keys.length - 1) {
                        queryString += "&";
                    }
                }
            }
            return queryString;
        }

    </script>
</head>
<body>
<jsp:include page="/views/common/mainHeader.jsp"></jsp:include>
<section>
    <a class="tab" href="#tab1">문자전송</a>
    <a class="tab" href="#tab2">발송내역</a>

</section>
<section>
    <div id="tab1" class="tabview">
        <h2 class="title">Title 1</h2>
        <div class="content">Content 1
            <div>
                <%--      수신자 / 템플릿 선택--%>
                <div data-columns="2">
                    <%--수신자 검색 / 수신 내역--%>
                    <div>
                        <%--          1--%>
                        <div>
                            <button id="smsList" onclick="javascript:test();">수신자</button>


                            <input type="text" placeholder="휴대폰 번호 또는 이름으로 검색 + Enter ">
                        </div>
                        <%--    2--%>
                        <div>
                            <label>수신자 목록</label>

                            <div class="dataArea">
                                <div class="dataSendingArea">
                                    <c:if test="${not empty data.smsReciver}">
                                        <c:forEach var="list" items="${data.smsReciver}" varStatus="status">
                                            <label> ${list.name}</label>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                            <div class="sms-history">
                                <div class="text-sms-his">
                                    <c:if test="${not empty data.smsHis}">
                                    <span>지난 발송 내역</span>
                                    <input id="smsP" type="radio" name="text_send" value ="P" checked="${data.smsHis.get(0).reciveType eq 'P'}"><label>학부모</label>
                                    <input id="smsS" type="radio" name="text_send" value ="S" checked="${data.smsHis.get(0).reciveType eq 'S'}"><label>학생</label>
                                    </c:if>
                                <c:forEach var="his" items="${dadta.smsHis}" varStatus="status">
                                    <label> ${his.sentDate}</label>
                                    <label> ${his.sentTime}</label>
                                    <c:if test="${his.sentResult eq '0'}">
                                        <label>발송 완료</label>
                                    </c:if>
                                    <c:if test="${his.sentResult eq '1'}">
                                        <label>발송 실패</label>
                                    </c:if>
                                    <lable>${his.text}</lable>
                                </c:forEach>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div>
                        <div>
                            <label>템플릿 선택</label>
                            <div class="dataArea">
                                <c:if test="${not empty data.templateList}">
                                    <c:forEach var="tempList" items="${data.templateList}" varStatus="status">
                                        <div onclick="javascript:fnShowContent('${tempList.content}');">
                                            <span><label class="testLabel"> ${tempList.title}</label></span>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                            <button id="smstemplate" onclick="javascript:test2();">설정</button>
                        </div>

                        <div>
                            <div class="sms-editor" id="smsEditor">
<%--                                <input type="text" id="smsEdite" name="smsEdite" style="width: 40em; height: 40em;">--%>
                                <textarea  id="smsEdite" name="smsEdite" style="width: 40em; height: 40em;"></textarea>
                            </div>
                            <button>전송하기</button>
                        </div>

                    </div>

                </div>

            </div>
        </div>
    </div>

    <div id="tab2" class="tabview">
        <h2 class="title">Title 2</h2>
        <div class="content">Content 2</div>
    </div>

</section>

<div id="subjectDialog" class="dialog-all-view" >
</div>

<div id="subjectDialog" class="dialog-template-view" >

</div>

</body>
<%--<script type="text/javascript" src="../../../js/searchFz.js"></script>--%>
</html>
