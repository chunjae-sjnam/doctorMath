<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="../../../js/student/studentSave.js"></script>
    <script src="../../../js/student/studentUpdate.js"></script>
    <title>학생 관리</title>
</head>
<style>
    table {
        border-collapse: collapse;
    }

    .pop_content {
        font-size: 13px;
        margin-left: 25px;
    }

    .pop_content tr {
        height: 40px;
    }

    #banner_online {
        height: 700px;
        width: 465px;
        border: 1px solid black;
        box-shadow: 3px 3px 7px 1px grey;
        background-color: white;
        z-index: 9999;
        margin-left: 36%;
        margin-top: -30%;
        display: none;
        position: fixed;
    }

    #banner_online h2 {
        text-align: center;
        font-size: 17px;
        margin-bottom: 10px;
    }

    #modal_detail, #modal_file {
        position:fixed;
        width:100%;
        height:100%;
        background:rgba(0, 0, 0, 0.5);
        top: 0;
        left: 0;
        z-index: 99;
        display: none;
    }

    #banner_file {
        height: 200px;
        width: 450px;
        border: 1px solid black;
        box-shadow: 3px 3px 7px 1px grey;
        background-color: white;
        z-index: 9999;
        margin-left: 36%;
        margin-top: -20%;
        display: none;
        position: fixed;
    }

    #close_button {
        float: right;
        margin-top: 2px;
    }

    input[id=excelFile] {
        width: 370px;
    }
</style>
<body>
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
                    <td>${fn:substring(list.name,0,1)}*${fn:substring(list.name,2,fn:length(list.name))}</td>
                    <td>${list.curri}${list.grade}</td>
                    <td>${list.status}</td>
                    <td>${list.userID}</td>
                    <td>
                        <c:if test="${!empty list.shtell}">${fn:substring(list.shtell,0,3)}-${fn:substring(list.shtell,3,7)}-${fn:substring(list.shtell,7,11)}</c:if>
                        <c:if test="${empty list.shtell}">미등록</c:if>
                    </td>
                    <td>${list.parentID}</td>
                    <td>
                        <c:if test="${!empty list.phtell}">${fn:substring(list.phtell,0,3)}-${fn:substring(list.phtell,3,7)}-${fn:substring(list.phtell,7,11)}</c:if>
                        <c:if test="${empty list.phtell}">미등록</c:if>
                    </td>
                    <td>
                        <button onclick="detailPopUp('${list.studentCode}')">보기</button>
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
                        <td>학생 연락처</td>
                        <td>
                            <input type="text" name="shtell" id="shtell" placeholder="숫자만 입력하세요."/>
                        </td>
                    </tr>
                    <tr>
                        <td>학부모 연락처</td>
                        <td>
                            <input type="text" name="phtell" id="phtell" placeholder="숫자만 입력하세요."/>
                        </td>
                    </tr>
                    <tr>
                        <td>학생 앱 ID</td>
                        <td>
                            <span name="studentID" id="studentID"></span>
                            <button type="button" onclick="defaultPassword(document.getElementById('studentCode').value);">비밀번호 초기화</button>
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
                        <td>학교</td>
                        <td>
                            <select name="sido" id="sido" onchange="sidoChange()">
                                <option value="" selected>시/도</option>
                            </select>
                            <select name="gu" id="gu" onchange="fnGuList()">
                                <option value="" selected>구/군</option>
                            </select>
                            <select name="school" id="school">
                                <option value="" selected>학교 선택</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>학생 이메일</td>
                        <td>
                            <input type="text" name="email_sub" id="email_sub" style="width: 70px;">
                            <span>@</span>
                            <input type="text" name="email_direct" id="email_direct" disabled style="width: 90px;"/>
                            <select name="email_domain" id="email_domain" onchange="emailChange()">
                                <option value="">선택</option>
                                <option value="naver.com" ${email_domain eq 'naver.com' ? 'selected' : ''}>naver.com</option>
                                <option value="gmail.com" ${email_domain eq 'gmail.com' ? 'selected' : ''}>gmail.com</option>
                                <option value="daum.net" ${email_domain eq 'daum.net' ? 'selected' : ''}>daum.net</option>
                                <option value="nate.com" ${email_domain eq 'nate.com' ? 'selected' : ''}>nate.com</option>
                                <option value="direct">직접 입력</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>집 주소</td>
                        <td>
                            <input type="number" name="postNum" id="postNum" maxlength="6" style="width: 60px"><br>
                            <input type="text" name="addr1" id="addr1" placeholder="주소를 입력하세요."/>
                        </td>
                    </tr>
                    <tr>
                        <td>집 전화</td>
                        <td>
                            <input type="text" name="tell" id="tell" placeholder="숫자만 입력하세요."/>
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
                            <input type="text" name="classDate" id="classDate" autocomplete="off" placeholder="YYYY-MM-DD"/>
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
                <input type="button" onclick="detailSave(document.getElementById('studentCode').value);" value="저장">
            </div>
        </form>
    </div>
    <div id="modal_file">
    </div>
    <div id="banner_file">
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
    <div id="modal_register">
    </div>
    <div id="banner_register">
        <div>
            <p>개별</p>
            <form name="insertForm">
                <div class="pop_content">
                    <table border="1">
                        <tr>
                            <td>이름</td>
                            <td>
                                <input type="text" id="name_i" name="name_i" placeholder="이름을 입력하세요." maxlength="10"/>
                            </td>
                        </tr>
                        <tr>
                            <td>학년</td>
                            <td>
                                <select id="curri_i" name="curri_i" onchange="curriChange_i(this)">
                                    <option value="">선택</option>
                                    <option value="E">초등</option>
                                    <option value="M">중등</option>
                                    <option value="H">고등</option>
                                </select>

                                <select id="grade_i" name="grade_i">
                                    <option value="">선택</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>상태</td>
                            <td>
                                <div class="radio-form">
                                    <input type="radio" name="status" value="S">
                                    <label for="status1">재원</label>
                                    <input type="radio" name="status" value="P">
                                    <label for="status2">휴원</label>
                                    <input type="radio" name="status" value="O">
                                    <label for="status3">퇴원</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>학생 연락처</td>
                            <td>
                                <input type="text" name="shtell_i" id="shtell_i" placeholder="숫자만 입력하세요."/>
                            </td>
                        </tr>
                        <tr>
                            <td>학부모 연락처</td>
                            <td>
                                <input type="text" name="phtell_i" id="phtell_i" placeholder="숫자만 입력하세요."/>
                            </td>
                        </tr>
                        <tr>
                            <td>학생 앱 ID</td>
                            <td>
                                <span name="studentID_i" id="studentID_i"></span>
                                <span style="font-size: 11px">S+(학생 연락처 8자리) 자동 입력 (없을 경우 임의생성)</span>
                            </td>
                        </tr>
                        <tr>
                            <td>학부모 앱 ID</td>
                            <td>
                                <span name="parentID_i" id="parentID_i"></span>
                                <span style="font-size: 11px">S+(학생 연락처 8자리) 자동 입력 (없을 경우 임의생성)</span>
                            </td>
                        </tr>
                        <tr>
                            <td>학교</td>
                            <td>
                                <form action="/operation/sidoList" method="post" name="sidoList">
                                    <select name="sido_i" id="sido_i" onclick="sidoList_i()" onchange="guList_i()">
                                        <option value="">시/도</option>
                                    </select>
                                </form>
                                <select name="gu_i" id="gu_i">
                                    <option value="" selected>구/군</option>
                                </select>
                                <select name="school_i" id="school_i">
                                    <option value="" selected>학교 선택</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>학생 이메일</td>
                            <td>
                                <input type="text" name="email_sub_i" id="email_sub_i" style="width: 70px;">
                                <span>@</span>
                                <input type="text" name="email_direct_i" id="email_direct_i" disabled style="width: 90px;"/>
                                <select name="email_domain_i" id="email_domain_i" onchange="emailChange_i()">
                                    <option value="">선택</option>
                                    <option value="naver.com" ${email_domain eq 'naver.com' ? 'selected' : ''}>naver.com</option>
                                    <option value="gmail.com" ${email_domain eq 'gmail.com' ? 'selected' : ''}>gmail.com</option>
                                    <option value="daum.net" ${email_domain eq 'daum.net' ? 'selected' : ''}>daum.net</option>
                                    <option value="nate.com" ${email_domain eq 'nate.com' ? 'selected' : ''}>nate.com</option>
                                    <option value="direct">직접 입력</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>집 주소</td>
                            <td>
                                <input type="number" name="postNum_i" id="postNum_i" maxlength="6" style="width: 60px"><br>
                                <input type="text" name="addr1_i" id="addr1_i" placeholder="주소를 입력하세요."/>
                            </td>
                        </tr>
                        <tr>
                            <td>집 전화</td>
                            <td>
                                <input type="text" name="tell_i" id="tell_i" placeholder="숫자만 입력하세요."/>
                            </td>
                        </tr>
                        <tr>
                            <td>생년월일</td>
                            <td>
                                <input type="text" name="birth_i" id="birth_i" autocomplete="off" placeholder="YYYY-MM-DD"/>
                            </td>
                        </tr>
                        <tr>
                            <td>수업 시작일</td>
                            <td>
                                <input type="text" name="classDate_i" id="classDate_i" autocomplete="off" placeholder="YYYY-MM-DD"/>
                            </td>
                        </tr>
                        <tr>
                            <td>비고</td>
                            <td>
                                <input type="text" name="memo_i" id="memo_i" placeholder="내용을 입력하세요."/>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
        <div class="pop_bottom" align="center">
            <button type="button" onclick="goBack();">취소</button>
            <button type="button" onclick="registerSave();">저장</button>
        </div>
    </div>
</body>
<script type="text/javascript">
    
    function sidoList_i() {
        $("form[name=sidoList]").submit();
    }

    //비밀번호 초기화
    function defaultPassword(studentCode) {

    }

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

    //상세정보 보기
    function detailPopUp(studentCode){
        $.ajax({
            type: "POST",
            url: '/operation/getDetail',
            data: {
                studentCode: studentCode
            },
            success: function (data) {

                $("#banner_online").fadeIn();
                $("#modal_detail").fadeIn();

                $("#studentCode").val(data.studentCode);
                $("#name").val(data.name);      //이름
                $("#curri").val(data.curri);    //학교급
                $("#grade").val(data.grade);    //학년

                if(data.status == "S"){         //상태
                    $("input:radio[name='status']:radio[value='S']").attr("checked", true);

                } else if(data.Status == "P"){
                    $("input:radio[name='status']:radio[value='P']").attr("checked", true);

                } else {
                    $("input:radio[name='status']:radio[value='O']").attr("checked", true);
                }

                var email = data.email; //이메일
                var email_domain = email.substring(email.indexOf("@")+1);

                $("#email_sub").val(email.substring(0, email.indexOf("@")));

                if(email_domain == ""){
                    $("#email_domain").val("");

                } else if(email_domain == "naver.com" || email_domain == "gmail.com" || email_domain == "daum.net" || email_domain == "nate.com"){
                    $("#email_domain").val(email.substring(email.indexOf("@")+1));

                } else {
                    $("#email_domain").val("direct");
                    $("#email_direct").val(email.substring(email.indexOf("@")+1));
                    emailChange();
                }

                $("#studentID").text(data.userID);      //학생 ID
                $("#parentID").text(data.parentID);     //학부모 ID

                $("#shtell").val(data.shtell);          //학생 연락처
                $("#phtell").val(data.phtell);          //학부모 연락처

                $("#sido").val(data.sido);
                $("#gu").val(data.gu);
                $("#school").val(data.school);          //학교
                $("#postNum").val(data.postNum);        //우편번호
                $("#addr1").val(data.addr1);            //주소

                $("#tell").val(data.tell);              //집전화
                $("#birth").val(data.birth);            //생년월일
                $("#classDate").val(data.classDate);    //수업 시작일
                $("#memo").val(data.memo);              //비고
            },
            error : function(request, status, error){
                alert("Error :" + error);
            }
        });
    }

    //등록 팝업
    $(document).ready(function (){
        $("#close_button_register").click(function(){
            $("#banner_file").fadeOut();
            $("#modal_file").fadeOut();
        });
    });

    //학생 등록
    function register() {
        $("#banner_file").fadeIn();
        $("#modal_file").fadeIn();
        $("#inputFile").val('');
        $("#excelFile").val('');
    }

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

    //취소
    function goBack() {
        location.href = "/operation/list";
    }
</script>
</html>
