<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <title>원관리</title>
</head>
<style>
    table {
        border-collapse: collapse;
    }

    .pop_content tr {
        height: 40px;
    }

    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }

    input[id=excelFile] {
        width: 370px;
    }

    input[type=number] {
        -moz-appearance: textfield;
    }

    #banner_online {
        height: 700px;
        width: 480px;
        border: 1px solid black;
        box-shadow: 3px 3px 7px 1px grey;
        background-color: white;
        z-index: 9999;
        margin-left: 36%;
        margin-top: -75%;
        display: none;
        position: fixed;
    }

    #banner_register {
        height: 200px;
        width: 450px;
        border: 1px solid black;
        box-shadow: 3px 3px 7px 1px grey;
        background-color: white;
        z-index: 9999;
        margin-left: 36%;
        margin-top: -70%;
        display: none;
        position: fixed;
    }

    #banner_online h2 {
        text-align: center;
        font-size: 17px;
        margin-bottom: 10px;
    }

    .pop_content {
        font-size: 13px;
        margin-left: 20px;
    }

    #close_button, #close_button_register {
        float: right;
        margin-top: 2px;
    }

    #modal_detail, #modal_register {
        position:fixed;
        width:100%;
        height:100%;
        background:rgba(0, 0, 0, 0.5);
        top: 0;
        left: 0;
        z-index: 99;
        display: none;
    }

    .list_btn {
        margin-bottom: 20px;
    }
</style>
<body>
<div id="wrap">
    <h3>학생 관리</h3>
    <div class="list_btn">
        <div>
            <select id="filter" name="filter">
                <option value="">전체</option>
                <option value="name">이름</option>
                <option value="grade">학년</option>
                <option value="status">상태</option>
                <option value="phone">연락처</option>
            </select>
            <input name="search" id="search" placeholder="검색"/>
            <button type="submit">검색</button>
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
            <c:forEach var="list" items="${resultMap.list}" varStatus="status">
                <c:set var="i" value="${i+1}"/>
                <tr align="center" style="height:40px">
                    <td class="first">
                        <input type="checkbox" name="checkbox" onclick="check()"/>
                    </td>
                    <td>${i}</td>
                    <td>${list.Name}</td>
                    <td>${list.Curri}${list.Grade}</td>
                    <td>${list.Status}</td>
                    <td>${list.UserID}</td>
                    <td>
                        <c:if test="${!empty list.SHtell}">${fn:substring(list.SHtell,0,3)}-${fn:substring(list.SHtell,3,7)}-${fn:substring(list.SHtell,7,11)}</c:if>
                        <c:if test="${empty list.SHtell}">미등록</c:if>
                    </td>
                    <td>${list.UserID}</td>
                    <td>
                        <c:if test="${!empty list.PHtell}">${fn:substring(list.PHtell,0,3)}-${fn:substring(list.PHtell,3,7)}-${fn:substring(list.PHtell,7,11)}</c:if>
                        <c:if test="${empty list.PHtell}">미등록</c:if>
                    </td>
                    <td>
                        <button type="button" onclick="detailPopup('${list.StudentCode}')">보기</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div id= "modal_detail">
    </div>
    <div id="banner_online">
        <div id="close_button" style ="cursor: pointer;">
            <img src="/images/close_button.png" width="12px" height="12px">
        </div>
        <p>학생 상세 정보</p>
        <form name="updateForm">
            <input type="hidden" name="studentCode" id="studentCode"/>
            <div class="pop_content">
                <table border="1">
                    <tr>
                        <td>이름</td>
                        <td>
                            <input type="text" id="name" name="name" placeholder="이름을 입력하세요." maxlength="10"/>
                        </td>
                    </tr>
                    <tr>
                        <td>학년</td>
                        <td>
                            <select id="curri" name="curri" onchange="curriChange()">
                                <option value="">학교급</option>
                                <option value="E" ${curri.val eq 'E' ? 'selected' : ''}>초등</option>
                                <option value="M" ${curri.val eq 'M' ? 'selected' : ''}>중등</option>
                                <option value="H" ${curri.val eq 'H' ? 'selected' : ''}>고등</option>
                            </select>

                            <select id="grade" name="grade">
                                <option value="">학년</option>
                                <c:choose>
                                    <c:when test="${curri.val eq 'E'}">
                                        <option value="1" ${grade.val eq '1' ? 'selected' : ''}>1학년</option>
                                        <option value="2" ${grade.val eq '2' ? 'selected' : ''}>2학년</option>
                                        <option value="3" ${grade.val eq '3' ? 'selected' : ''}>3학년</option>
                                        <option value="4" ${grade.val eq '4' ? 'selected' : ''}>4학년</option>
                                        <option value="5" ${grade.val eq '5' ? 'selected' : ''}>5학년</option>
                                        <option value="6" ${grade.val eq '6' ? 'selected' : ''}>6학년</option>
                                    </c:when>
                                    <c:when test="${curri.val ne 'E'}">
                                        <option value="1" ${grade.val eq '1' ? 'selected' : ''}>1학년</option>
                                        <option value="2" ${grade.val eq '2' ? 'selected' : ''}>2학년</option>
                                        <option value="3" ${grade.val eq '3' ? 'selected' : ''}>3학년</option>
                                        <option value="4" ${grade.val eq '4' ? 'selected' : ''}>4학년</option>
                                        <option value="5" ${grade.val eq '5' ? 'selected' : ''}>5학년</option>
                                        <option value="6" ${grade.val eq '6' ? 'selected' : ''}>6학년</option>
                                    </c:when>
                                </c:choose>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>상태</td>
                        <td>
                            <div class="radio-form">
                                <input type="radio" name="status" id="status1" value="S">
                                <label for="status1">재원</label>
                                <input type="radio" name="status" id="status2" value="P">
                                <label for="status2">휴원</label>
                                <input type="radio" name="status" id="status3" value="O">
                                <label for="status3">퇴원</label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>학생 앱 ID</td>
                        <td>
                            <span name="studentID" id="studentID"></span>
                            <button type="button">비밀번호 초기화</button>
                            <span style="font-size: 11px">*초기 비밀번호 0000</span>
                        </td>
                    </tr>
                    <tr>
                        <td>학부모 앱 ID</td>
                        <td>
                            <span name="parentID" id="parentID"></span>
                            <button type="button">비밀번호 초기화</button>
                            <span style="font-size: 11px">*초기 비밀번호 0000</span>
                        </td>
                    </tr>
                    <tr>
                        <td>학생 연락처</td>
                        <td>
                            <input type="number" name="SHtell" id="SHtell" placeholder="숫자만 입력하세요."/>
                        </td>
                    </tr>
                    <tr>
                        <td>학부모 연락처</td>
                        <td>
                            <input type="number" name="PHtell" id="PHtell" placeholder="숫자만 입력하세요."/>
                        </td>
                    </tr>
                    <tr>
                        <td>학교</td>
                        <td>
                            <input type="text" name="schoolName" id="schoolName" placeholder="학교명을 입력하세요." maxlength="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td>학생 이메일</td>
                        <td>
                            <input type="text" name="email_sub" id="email_sub">
                            <span>@</span>
                            <select name="email_domain" id="email_domain">
                                <option value="">직접 입력</option>
                                <option value="naver.com" ${email_domain eq 'naver.com' ? 'selected' : ''}>naver.com</option>
                                <option value="gmail.com" ${email_domain eq 'gmail.com' ? 'selected' : ''}>gmail.com</option>
                                <option value="daum.net" ${email_domain eq 'daum.net' ? 'selected' : ''}>daum.net</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>집 주소</td>
                        <td>
                            <input type="number" name="postNum" id="postNum" maxlength="6" style="width: 60px"><br>
                            <input type="text" name="address" id="address" placeholder="주소를 입력하세요." value="${list.Addr1}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>집 전화</td>
                        <td>
                            <input type="number" name="tell" id="tell" placeholder="숫자만 입력하세요."/>
                        </td>
                    </tr>
                    <tr>
                        <td>생년월일</td>
                        <td>
                            <input type="text" name="birth" id="birth" autocomplete="off" placeholder="YYYY-MM-DD"/>
                        </td>
                    </tr>
                    <tr>
                        <td>수업 시작일</td>
                        <td>
                            <input type="text" name="creDate" id="creDate" autocomplete="off" placeholder="YYYY-MM-DD"/>
                        </td>
                    </tr>
                    <tr>
                        <td>비고</td>
                        <td>
                            <input type="text" name="memo" id="memo" placeholder="내용을 입력하세요."/>
                        </td>
                    </tr>
                </table>
            </div><hr>
            <div class="pop_bottom" align="center">
                <button type="button" onclick="goBack();">취소</button>
                <button type="button" onclick="goForm();">저장</button>
            </div>
        </form>
    </div>
    <div id="modal_register">
    </div>
    <div id="banner_register">
        <div id="close_button_register" style ="cursor: pointer;">
            <img src="/images/close_button.png" width="12px" height="12px">
        </div>
        <div class="file_area">
            <p>일괄</p>
            <form id="form" name="form" method="post" enctype="multipart/form-data">
                <div style="margin-left: 15px;">
                    <div>
                        <input type="file" id="inputFile" style="display: none;" accept=".xlsx, .xls"
                               onchange="document.getElementById('excelFile').value=this.value.replace(/C:\\fakepath\\/i,'');">
                        <input type="text" id="excelFile" placeholder="파일 끌어놓기 또는 첨부로 학생 명단을 추가하세요." readonly/>
                        <a href="#" onclick="document.getElementById('inputFile').click();">첨부</a>
                    </div>
                    <span style="font-size: 10px">*최대 00Mb까지 등록이 가능합니다.</span>
                </div>
            </form>
        </div>
        <div align="center" style="margin: 15px 0px">
            <a href="/operation/getDownload?fileName=닥터매쓰 학생 등록 양식.xls">파일 다운로드</a>
        </div>
        <div class="pop_bottom" align="center">
            <button type="button" onclick="goBack();">취소</button>
            <button type="button" onclick="fileUpload(this);">등록</button>
        </div>
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
    
    //학생 등록
    function register() {
        $("#banner_register").fadeIn();
        $("#modal_register").fadeIn();
        $("#inputFile").val('');
        $("#excelFile").val('');
    }
    
    //학생 삭제
    function getDelete() {
        
    }

    //상세정보 보기
    function detailPopup(StudentCode){
        $.ajax({
            type: "POST",
            url: '/operation/detailList',
            data: {
                StudentCode: StudentCode
            },
            success: function (data) {
                if(data.result){

                    $("#banner_online").fadeIn();
                    $("#modal_detail").fadeIn();

                    const list = data.detailList[0];

                    $("#studentCode").val(list.StudentCode);
                    $("#name").val(list.Name);      //이름
                    $("#curri").val(list.Curri);    //학교급
                    $("#grade").val(list.Grade);    //학년

                    if(list.Status == "S"){         //상태
                        $("input:radio[name='status']:radio[value='S']").attr("checked", true);

                    } else if(list.Status == "P"){
                        $("input:radio[name='status']:radio[value='P']").attr("checked", true);

                    } else {
                        $("input:radio[name='status']:radio[value='O']").attr("checked", true);
                    }

                    $("#studentID").text(list.UserID);  //학생 ID
                    $("#parentID").text(list.ParentID);   //학부모 ID

                    $("#SHtell").val(list.SHtell);      //학생 연락처
                    $("#PHtell").val(list.PHtell);      //학부모 연락처
                    $("#schoolName").val(list.SchoolName);  //학교

                    var email = list.Email; //이메일
                    $("#email_sub").val(email.substring(0, email.indexOf("@")));
                    $("#email_domain").val(email.substring(email.indexOf("@")+1));

                    $("#postNum").val(list.PostNum);  //우편번호
                    $("#address").val(list.Addr1);  //주소
                    $("#tell").val(list.Tell);      //집전화

                    $("#birth").val(list.Birth);        //생년월일
                    $("#creDate").val(list.CreDate);    //수업시작일
                    $("#memo").val(list.Memo);  //비고
                    
                } else {
                    alert("오류 발생");
                }
            },
            error: function (jqXHR, status, error){
                alert('에러: ' + error);
            }
        });
    }

    //상세 정보 보기 팝업
    $(document).ready(function (){
        $("#close_button").click(function(){
            $("#banner_online").fadeOut();
            $("#modal_detail").fadeOut();
        });
    });

    //등록 팝업
    $(document).ready(function (){
        $("#close_button_register").click(function(){
            $("#banner_register").fadeOut();
            $("#modal_register").fadeOut();
        });
    });
    
    //파일 업로드
    function fileUpload() {

        const inputFile = document.getElementById("inputFile");
        var formData = new FormData(document.getElementById('form'));
        formData.append("excel_file", inputFile.files[0]);

        var file = document.getElementById("excelFile").value;

        if(file == "" || file == null){
            alert("파일을 선택해주세요.");
            return false;

        } else if(!checkFileType(file)){
            alert("excel 파일만 업로드 가능합니다.");
            return false;
        }

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            url: "/operation/fileUpload",
            data: formData,
            success: function (result) {
                if(result.code == "SUCCESS"){
                    alert(result.msg);  //등록 성공

                } else {
                    alert(result.msg);  //등록 실패
                }
            },
            error: function (result, e) {
                // alert(result.msg);  //등록 실패
                alert("등록 실패");
            }
        });
    }

    function checkFileType(filePath) {
        var fileFormat = filePath.split(".");

        if (fileFormat.indexOf("xls") > -1 || fileFormat.indexOf("xlsx") > -1) {
            return true;
        } else {
            return false;
        }
    }

    //학교급 변경 시 학년 변경
    function curriChange() {
        $("#grade option").remove();    //option 초기화

        let html = '';

        html += '<option value="">학년</option>';
        let curri = $("#curri option:selected").val();

        //학교급이 중등, 고등 일 경우 학년 : 1,2,3
        if(curri == 'M' || curri == 'H'){
            html += '<option value="1" ${grade.val eq '1' ? 'selected' : ''}>1학년</option>';
            html += '<option value="2" ${grade.val eq '2' ? 'selected' : ''}>2학년</option>';
            html += '<option value="3" ${grade.val eq '3' ? 'selected' : ''}>3학년</option>';

        } else if (curri == "E"){ //학교급이 초등 일 경우 1~6
            html += '<option value="1" ${grade.val eq '1' ? 'selected' : ''}>1학년</option>';
            html += '<option value="2" ${grade.val eq '2' ? 'selected' : ''}>2학년</option>';
            html += '<option value="3" ${grade.val eq '3' ? 'selected' : ''}>3학년</option>';
            html += '<option value="4" ${grade.val eq '4' ? 'selected' : ''}>4학년</option>';
            html += '<option value="5" ${grade.val eq '5' ? 'selected' : ''}>5학년</option>';
            html += '<option value="6" ${grade.val eq '6' ? 'selected' : ''}>6학년</option>';

        } else {
        }
        $("#grade").append(html);
    }

    $(document).ready(function () {
        $(function () {

            //생년월일
            $('#birth').datepicker({
                lang:'ko',
                dateFormat: 'yy-mm-dd',
                monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], //달력의 월 부분 텍스트
                monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], //달력의 월 부분 Tooltip
                dayNamesMin: ['일','월','화','수','목','금','토'], //달력의 요일 텍스트
                dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'], //달력의 요일 Tooltip
                yearSuffix: '년',
                showMonthAfterYear: true,
                buttonImageOnly: true
            });

            $('#birth').keydown(function (event) {
                var key = event.charCode || event.keyCode || 0;
                $text = $(this);
                if (key !== 8 && key !== 9) {
                    if ($text.val().length === 4) {
                        $text.val($text.val() + '-');
                    }
                    if ($text.val().length === 7) {
                        $text.val($text.val() + '-');
                    }
                }
                return (key == 8 || key == 9 || key == 46 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
            });

            //수업 시작일
            $('#creDate').datepicker({
                lang:'ko',
                dateFormat: 'yy-mm-dd',
                monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], //달력의 월 부분 텍스트
                monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], //달력의 월 부분 Tooltip
                dayNamesMin: ['일','월','화','수','목','금','토'], //달력의 요일 텍스트
                dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'], //달력의 요일 Tooltip
                yearSuffix: '년',
                showMonthAfterYear: true,
                buttonImageOnly: true
            });

            $('#creDate').keydown(function (event) {
                var key = event.charCode || event.keyCode || 0;
                $text = $(this);
                if (key !== 8 && key !== 9) {
                    if ($text.val().length === 4) {
                        $text.val($text.val() + '-');
                    }
                    if ($text.val().length === 7) {
                        $text.val($text.val() + '-');
                    }
                }
                return (key == 8 || key == 9 || key == 46 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
            });
        });
    });

    //취소
    function goBack() {
        location.href = "/operation/list";
    }
    
    //저장
    function goForm() {
        let name = $("#name").val();
        let reg = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

        let studentCode = document.getElementById("studentCode").value;
        let curri = document.getElementById("curri").value;
        let grade = document.getElementById("grade").value;
        let status = $('input:radio[name="status"]:checked').val();
        let SHtell = document.getElementById("SHtell").value;
        let PHtell = document.getElementById("PHtell").value;
        let SchoolName = document.getElementById("schoolName").value;
        var email;

        if(document.getElementById("email_domain").value == ''){
            email = document.getElementById("email_sub").value;
        } else {
            email = document.getElementById("email_sub").value + "@" + document.getElementById("email_domain").value;
        }

        let address = document.getElementById("address").value;
        let postNum = document.getElementById("postNum").value;
        let tell = document.getElementById("tell").value;
        let birth = document.getElementById("birth").value;
        let creDate = document.getElementById("creDate").value;
        let memo = document.getElementById("memo").value;

        if(name == "") {
            alert("필수값을 빠짐없이 입력해 주세요.");
            $("#name").focus();
            return false;
        }

        if( reg.test(name) ) {
            alert("특수기호는 입력 불가합니다.");
            $("#name").focus();
            return false;
        }

        if($("#curri").val() == ""){
            alert("필수값을 빠짐없이 입력해 주세요.");
            $("#curri").focus();
            return false;
        }

        if($("#grade").val() == ""){
            alert("필수값을 빠짐없이 입력해 주세요.");
            $("#grade").focus();
            return false;
        }

        if(confirm("저장하시겠습니까?")){

            let formData = {
                "StudentCode": studentCode,
                "Name": name,
                "Curri": curri,
                "Grade": grade,
                "Status": status,
                "SHtell": SHtell,
                "PHtell": PHtell,
                "SchoolName": SchoolName,
                "Email": email,
                "PostNum": postNum,
                "Addr1": address,
                "Tell": tell,
                "Birth": birth,
                "CreDate": creDate,
                "Memo": memo
            };

            $.ajax({
                type: "POST",
                url: '/operation/updateList',
                cache: false,
                data: formData,
                dataType:'json',
                success:function (data){
                    if(data.result) {
                        alert("저장되었습니다.");
                        location.href = "/operation/list";
                    } else {
                        alert("저장에 실패하였습니다.");
                        location.href = "/operation/list";
                    }
                },
                error: function (request,status,error){
                    alert("error code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                }
            });
        }
    }
</script>
</html>