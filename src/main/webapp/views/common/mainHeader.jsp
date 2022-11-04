<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>

<%--    <script type="text/javascript" src="../../../js/common.js"></script>--%>
    <script>
        $(function(){
            let joinBtn = $('.btn-site');

            joinBtn.on('click', function(){
                let _this = $(this);
                let navId =_this.attr('id');

                _this.siblings('button').removeClass('active');
                _this.addClass('active');

                // 2Depth로 이동
                var childNode =document.getElementById(navId+"2");
                childNode.style.display="block";
                location.href=childNode.firstElementChild.href;

            })

            $('.mobile-table input').on('focusout', function(){
                if(!$(this).val()){
                    $(this).next('label.placeholder').show();
                } else {
                    $(this).next('label.placeholder').hide();
                }
            })
        })
    </script>
</head>
<body>

<nav class="btn-wrap">
    <a href="#" class="btn-site">수업준비</a>
    <a href="#" class="btn-site" id="preClass">수업진행</a>
    <a href="#" class="btn-site" id="operation" style="">운영관리</a>
    <a href="#" class="btn-site">결제관리</a>

</nav>

<nav class="depth2" id="operation2" style="display: none">
    <a href="#" class="">원관리</a>
    <a href="/main/sendText" class="">문자관리</a>
    <a href="#" class="">출결관리</a>


</nav>

<a href="#none" class="name"><%=request.getSession().getAttribute("USER_NAME") %></a>

<a href="/main/logout" class="btn_default btn_gray right">로그아웃</a>
</body>
</html>
