//학생 상세 정보 저장
const detailSave = (studentCode) => {
    let name = document.getElementById("name").value;
    let reg = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

    let curri = document.getElementById("curri").value;
    let grade = document.getElementById("grade").value;
    let status = $('input:radio[name="status"]:checked').val();
    let shtell = document.getElementById("shtell").value;
    let phtell = document.getElementById("phtell").value;
    let sido = document.getElementById("sido").value;
    let gu = document.getElementById("gu").value;
    let school = document.getElementById("school").value;
    var email;

    if(document.getElementById("email_domain").value == "direct"){
        email = document.getElementById("email_sub").value + "@" + document.getElementById("email_direct").value;

    } else if(document.getElementById("email_domain").value == ""){
        email = "";

    } else {
        email = document.getElementById("email_sub").value + "@" + document.getElementById("email_domain").value;
    }

    let postNum = document.getElementById("postNum").value;
    let addr1 = document.getElementById("addr1").value;
    let tell = document.getElementById("shtell").value;
    let birth = document.getElementById("birth").value;
    let classDate = document.getElementById("classDate").value;
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

    if($("#school").val() != ""){
        if($("#sido").val() == ""){
            alert("시/도를 선택해 주세요.");
            $("#sido").focus();
            return false;
        }

        if($("#gu").val() == ""){
            alert("구/군을 선택해 주세요.");
            $("#gu").focus();
            return false;
        }
    }

    if($("#email_sub").val() != ""){
        if($("#email_domain").val() == ""){
            alert("이메일을 선택해주세요.");
            $("#email_domain").focus();
            return false;
        }

        if($("#email_domain").val() == "direct"){
            alert("이메일을 입력해주세요.");
            $("#email_direct").focus();
            return false;
        }
    }

    if(confirm("저장하시겠습니까?")){

        let formData = {
            "studentCode": studentCode,
            "name": name,
            "curri": curri,
            "grade": grade,
            "status": status,
            "shtell": shtell,
            "phtell": phtell,
            "sido": sido,
            "gu": gu,
            "school": school,
            "email": email,
            "postNum": postNum,
            "addr1": addr1,
            "tell": tell,
            "birth": birth,
            "classDate": classDate,
            "memo": memo
        };

        $.ajax({
            type: "POST",
            url: '/operation/update',
            cache: false,
            data: formData,
            dataType:'json',
            success:function (data){

                if(data == 1){
                    alert("저장되었습니다.");
                    location.href = "/operation/list";

                } else {
                    alert("저장에 실패하였습니다.");
                    location.href = "/operation/list";
                }
            }
        });
    }
}

//시도 리스트
const sidoList = () => {
    $.ajax({
        type: "POST",
        url: '/operation/sidoList',
        cache: false,
        success: function (data) {

        },
        error: function (jqXHR, status, error) {
            alert(status);
        }
    });
}

//학교급 변경 시 학년 변경
const curriChange = () => {

    $("#grade option").remove();    //option 초기화

    let html = '';
    html += '<option value="">선택</option>';
    let curri = document.getElementById("curri").value;

    //학교급이 중등, 고등 일 경우 학년 : 1,2,3
    if(curri == 'M' || curri == 'H'){
        html += '<option value="1">1학년</option>';
        html += '<option value="2">2학년</option>';
        html += '<option value="3">3학년</option>';

    } else if (curri == "E"){ //학교급이 초등 일 경우 1~6
        html += '<option value="1">1학년</option>';
        html += '<option value="2">2학년</option>';
        html += '<option value="3">3학년</option>';
        html += '<option value="4">4학년</option>';
        html += '<option value="5">5학년</option>';
        html += '<option value="6">6학년</option>';
    }
    $("#grade").append(html);
}

//이메일 변경 시
const emailChange = () => {
    let option = $("#email_domain option:selected").val();

    if(option == "direct"){ //직접 입력
        $("#email_direct").prop("disabled", false);
    } else {
        $("#email_direct").prop("disabled", true);
        $("#email_direct").val("");
    }
}

//상세 정보 보기 팝업
$(document).ready(function () {
    $("#close_button").click(function () {
        $("#banner_online").fadeOut();
        $("#modal_detail").fadeOut();
    });
});

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
        $('#classDate').datepicker({
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

        $('#classDate').keydown(function (event) {
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