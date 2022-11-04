<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>


<%--    <script type="text/javascript" src="../../../js/jquery.treeview.js"></script>--%>
<%--    <link rel="stylesheet" href="../../../css/jquery.treeview.css"/>--%>
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

        .dataArea {
            height: available;
            background: #dde8f1;
        }

        .testLabel {
            background: #f1b200;
        }

        .sms-history {
            height: 700px;
            background-color: coral;
        }

        .ext-sms-his {
            background-color: cornsilk;
        }


    </style>

    <script>
        $(document).ready(function(){
            // $(".nameSpaceClass").click(function (){
            //     $(this).remove();
            // })
        });

        $("#browser").treeview({
            toggle: function () {
                console.log("%s was toggled.", $(this).find(">span").text());
            }
        });

        function fnShowName(idValue) {
            if ($('#ul' + idValue).css("display") == "none") {
                $('#ul' + idValue).css('display', 'block'); //전체보기 보이기
            } else {
                $('#ul' + idValue).css('display', 'none');
            }

        }

        function fnSelId(keyVal, nameVal){
            $('#spanTxt').css('display', 'none');
            let html='';
            html += '<input type="button" class="nameSpaceClass" id="keyVal'+keyVal+'" name="keyVal" value="'+nameVal+'" readonly="true" onclick="fnRemove(this)">';
            $('#spaceSelName').append(html);

        }

        // function fnRemove(e){
        //
        //         $(e).remove();
        //
        // }





    </script>
</head>
<div class="popup_content">
    <div class="modal-header">
        <h5 class="modal-title" id="historyModalLabel">ID - Modal</h5>
        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
        </button>
    </div>
    <div>
        <input id="smsC" type="radio" name="text_send" value="P"><label>전체</label>
        <input id="smsP" type="radio" name="text_send" value="P" checked><label>학부모</label>
        <input id="smsS" type="radio" name="text_send" value="S"><label>학생</label>
        <div data-columns="2">
            <div>
        <input type="text" placeholder="검색 ">
        <section>
            <a class="tab" href="#tabTree1">클래스</a>
            <a class="tab" href="#tabTree2">학년</a>

        </section>
        <section>
            <div id="tabTree1" class="tabview">
                <h2 class="title">Title 1</h2>
                <div class="content">Content 1

                    <div>

<%--                            클래스 트리--%>
                            <div>
                                <ul class="filetree treeview">

                                    <ul>
                                        <c:forEach var="lchapter" items="${tree.getRoot().getChildren()}">
                                            <li class="expandable" id="${lchapter.getKey()}"
                                                value="${lchapter.getKey()}">
                                                <div class="hitarea collapsable-hitarea"></div>
                                                <span class="folder" onclick="fnShowName('${lchapter.getKey()}');">
                                                        ${lchapter.getText()}
                                                </span>
                                                <ul style="display: none;" id="ul${lchapter.getKey()}">
                                                    <c:forEach var="mchapter" items="${lchapter.getChildren()}">
                                                        <li class="collapsable" id="${mchapter.getKey()}"
                                                            value="${mchapter.getKey()}">
                                                            <div class="hitarea collapsable-hitarea"></div>
                                                            <span class="folder" onclick="fnSelId('${mchapter.getKey()}', '${mchapter.getText()}')">
                                                                    ${mchapter.getText()}
                                                            </span>

                                                        </li>
                                                    </c:forEach>

                                                </ul>
                                            </li>
                                        </c:forEach>

                                    </ul>
                                </ul>
                            </div>




                        </div>
                    </div>
                </div>


            <div id="tabTree2" class="tabview">
                <h2 class="title">Title 2</h2>
                <div class="content">Content 2</div>
            </div>

        </section>
            </div>

            <div class="column2Class">
                <%--                            선택된 학생 보여주는 area--%>
                <div id="spaceSelName" >
                    <span id="spanTxt"> 수신자를 선택해 주세요</span>

                    <input type="button" class="nameSpaceClass" id="keyVal" name="keyValtest" value="test" readonly="true">

                </div>

            </div>

        </div>
        <%--버튼 영역--%>
<%--        <div>--%>
<%--            <div>--%>
<%--                <input type="button" value="취소">--%>
<%--                <input type="button" value="선택">--%>
<%--            </div>--%>
<%--        </div>--%>
    </div>
</div>
</body>
</html>
