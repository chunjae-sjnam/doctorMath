<%--
  Created by IntelliJ IDEA.
  User: NAMSEOYUN
  Date: 2022-11-11
  Time: 오후 4:18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>
    <title>학생관리</title>
</head>
<body>
<div id="wrap">
    <h3>학생 관리</h3>
    <div class="list_btn">
        <div>
            <form action="/operation/list" method="post">
                <select name="condition">
                    <option value=""<c:if test="${condition eq '' }">selected</c:if>>전체</option>
                    <option value="name"<c:if test="${condition eq 'name' }">selected</c:if>>이름</option>
                    <option value="grade"<c:if test="${condition eq 'grade' }">selected</c:if>>학년</option>
                    <option value="status"<c:if test="${condition eq 'status' }">selected</c:if>>상태</option>
                    <option value="phone"<c:if test="${condition eq 'phone' }">selected</c:if>>연락처</option>
                </select>
                <input type="text" name="keyword" value="${keyword }" placeholder="검색"/>
                <button type="submit">검색</button>
            </form>
        </div>
        <div>
            <button type="button" onclick="register();">등록</button>
            <button type="button" onclick="getDelete();">삭제</button>
        </div>
    </div>
    <div class="list_area">
        <table id="list_table" align="center" border="1">
            <colgroup>
                <col width="5%">
                <col width="7%">
                <col width="8%">
                <col width="8%">
                <col width="8%">
                <col width="10%">
                <col width="15%">
                <col width="15%">
                <col width="15%">
                <col width="10%">
            </colgroup>
            <thead>
            <tr align="center" border="1">
                <th scope="col" class="first">
                    <input type="checkbox" id="checkAll" name="checkAll" onclick="checkAll()" />
                </th>
                <th scope="col">구분</th>
                <th scope="col">이름</th>
                <th scope="col">학년</th>
                <th scope="col">상태</th>
                <th scope="col">로그인 정보</th>
                <th scope="col">연락처</th>
                <th scope="col">학부모 로그인 정보</th>
                <th scope="col">학부모 연락처</th>
                <th scope="col">상세 정보</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="list" items="${list}" varStatus="status">
                <tr align="center" style="height:40px">
                    <td class="first">
                        <input type="checkbox" name="checkbox" onclick="check()"/>
                    </td>
                    <td>${status.count}</td>
                    <td>${list.name}</td>
                    <td>${list.curri}${list.grade}</td>
                    <td>${list.status}</td>
                    <td>${list.userID}</td>
                    <td>
                        <c:if test="${!empty list.shtell}">${fn:substring(list.shtell,0,3)}-${fn:substring(list.shtell,3,7)}-${fn:substring(list.shtell,7,11)}</c:if>
                        <c:if test="${empty list.shtell}">미등록</c:if>
                    </td>
                    <td>${list.userID}</td>
                    <td>
                        <c:if test="${!empty list.phtell}">${fn:substring(list.phtell,0,3)}-${fn:substring(list.phtell,3,7)}-${fn:substring(list.phtell,7,11)}</c:if>
                        <c:if test="${empty list.phtell}">미등록</c:if>
                    </td>
                    <td>
                        <button type="button" onclick="detailPopup('${list.studentCode}')">보기</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
<script type="text/javascript">

    //전체 체크박스
    function checkAll() {
        $(document).ready(function() {

            if($("#checkAll").is(":checked")){
                $("input[name=checkbox]").prop("checked", true);

            } else {
                $("input[name=checkbox]").prop("checked", false);
            }
        });
    }

    //개별 체크박스
    function check() {
        var total = $("input[name=checkbox]").length;
        var checked = $("input[name=checkbox]:checked").length;

        if(total != checked){
            $("#checkAll").prop("checked", false);

        } else {
            $("#checkAll").prop("checked", true);
        }
    }
</script>
</html>
