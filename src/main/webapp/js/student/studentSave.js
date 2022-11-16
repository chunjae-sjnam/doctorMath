
    //학생 개별 저장
    const registerSave = () => {
        let name = document.getElementById("name_i").value;
        let reg = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

        let curri = document.getElementById("curri_i").value;
        let grade = document.getElementById("grade_i").value;
        let status = $('input:radio[name="status"]:checked').val();
        let shtell = document.getElementById("shtell_i").value;
        let phtell = document.getElementById("phtell_i").value;
        let sido = document.getElementById("sido_i").value;
        let gu = document.getElementById("gu_i").value;
        let school = document.getElementById("school_i").value;
        var email;

        if(document.getElementById("email_domain_i").value == "direct"){
            email = document.getElementById("email_sub_i").value + "@" + document.getElementById("email_direct_i").value;

        } else if(document.getElementById("email_domain_i").value == ""){
            email = "";

        } else {
            email = document.getElementById("email_sub_i").value + "@" + document.getElementById("email_domain_i").value;
        }

        let postNum = document.getElementById("postNum_i").value;
        let addr = document.getElementById("addr_i").value;
        let tell = document.getElementById("shtell_i").value;
        let birth = document.getElementById("birth_i").value;
        let classStart = document.getElementById("classStart_i").value;
        let memo = document.getElementById("memo_i").value;

        if(name == "") {
            alert("필수값을 빠짐없이 입력해 주세요.");
            $("#name_i").focus();
            return false;
        }

        if( reg.test(name) ) {
            alert("특수기호는 입력 불가합니다.");
            $("#name_i").focus();
            return false;
        }

        if($("#curri_i").val() == ""){
            alert("필수값을 빠짐없이 입력해 주세요.");
            $("#curri_i").focus();
            return false;
        }

        if($("#grade_i").val() == ""){
            alert("필수값을 빠짐없이 입력해 주세요.");
            $("#grade_i").focus();
            return false;
        }

        if($("#school_i").val() != ""){
            if($("#sido_i").val() == ""){
                alert("시/도를 선택해 주세요.");
                $("#sido_i").focus();
                return false;
            }

            if($("#gu_i").val() == ""){
                alert("구/군을 선택해 주세요.");
                $("#gu_i").focus();
                return false;
            }
        }

        if($("#email_sub_i").val() != ""){
            if($("#email_domain_i").val() == ""){
                alert("이메일을 선택해주세요.");
                $("#email_domain_i").focus();
                return false;
            }

            if($("#email_domain_i").val() == "direct"){
                alert("이메일을 입력해주세요.");
                $("#email_direct_i").focus();
                return false;
            }
        }

        if(confirm("저장하시겠습니까?")){

            let formData = {
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
                "addr": addr,
                "tell": tell,
                "birth": birth,
                "classStart": classStart,
                "memo": memo
            };

            $.ajax({
                type: "POST",
                url: '/operation/insert',
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

    //학교급 변경 시 학년 변경
    function curriChange_i() {

        $("#grade_i option").remove();    //option 초기화

        let html = '';
        html += '<option value="">선택</option>';
        let curri = document.getElementById("curri_i").value;

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
        $("#grade_i").append(html);
    }

    //이메일 변경 시
    function emailChange_i() {
        let option = $("#email_domain_i option:selected").val();

        if(option == "direct"){ //직접 입력
            $("#email_direct_i").prop("disabled", false);
        } else {
            $("#email_direct_i").prop("disabled", true);
            $("#email_direct_i").val("");
        }
    }
    
    //시도 리스트
    function sidoList_i() {

        $.ajax({
            type: "POST",
            url: '/operation/sidoList',
            cache: false,
            success:function (data){

            },
            error: function (jqXHR, status, error){
                alert(status);
            }
        });
    }

    $(document).ready(function () {
        $(function () {
            //생년월일
            $('#birth_i').datepicker({
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

            $('#birth_i').keydown(function (event) {
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
            $('#classStart_i').datepicker({
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

            $('#classStart_i').keydown(function (event) {
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