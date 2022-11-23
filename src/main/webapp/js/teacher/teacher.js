// 선생님 상세보기
const teacherDetail = (teacherCode) => {
    let html = ``;

    const reqData = {
        teacherCode : teacherCode
    }

    ajaxCall('post','/api/teacher/detail', reqData, function (resData) {
        console.log(resData);
        html = `
            <div class="bg"></div>
            <div class="modalBox">
                <table border="1" style="width: 100%;">
                    <tr>
                        <th>이름</th>
                        <td><input id="name_u" name="name_u" type="text" value="${resData.name}"></td>
                    </tr>
                    <tr>
                        <th>아이디</th>
                        <td>${resData.userId} <button onclick="defaultPassword('${teacherCode}');">비밀번호 초기화</button></td>
                    </tr>
                    <tr>
                        <th>연락처</th>
                        <td><input type="text" id="tell_u" name="tell_u" value="${resData.tell}"></td>
                    </tr>
                </table>
                <button class="closeBtn">✖</button>
                <button onclick="updateTeacher('${teacherCode}');">저장</button>
            </div>`

        document.querySelector("#teacherModal").innerHTML = html;
        document.querySelector(".closeBtn").addEventListener("click", close);
        document.querySelector(".bg").addEventListener("click", close);
    });

}

const open = () => {
    document.querySelector(".modal").classList.remove("hidden");
}

const close = () => {
    document.querySelector(".modal").classList.add("hidden");
}

// 비밀번호 초기화
const defaultPassword = (teacherCode) => {
    console.log("비밀번호 초기화" + teacherCode);
}

// 선생님 수정(이름, 연락처)
const updateTeacher = (teacherCode) => {
    console.log("선생님 수정 " + teacherCode);

    const reqData = {
         code : teacherCode
        ,name : document.getElementById('name_u').value
        ,tell : document.getElementById('tell_u').value
    }
    console.log(reqData);
    ajaxCall('post','/api/teacher/modify', reqData, function (resData) {
        console.log(resData)
        if (resData === 2) {
            alert('정상수정 완료');
        }
    });

}

// 등록
const insertTeacher = () => {
    console.log("등록간다")

    let name = document.getElementById("name_i").value;
    let reg = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

    let userId = document.getElementById("userId_i").value;
    let tell = document.getElementById("tell_i").value;

    if(name == "") {
        alert("이름을 입력해주세요.");
        $("#name_i").focus();
        return false;
    }

    if( reg.test(name) ) {
        alert("특수기호는 입력 불가합니다.");
        $("#name_i").focus();
        return false;
    }

    if(userId == "") {
        alert("아이디를 입력해주세요.");
        $("#userId_i").focus();
        return false;
    }

    if(confirm("저장하시겠습니까?")){

        const reqData = {
            name : name
            ,userId : userId
            ,tell : tell
        }
        console.log(reqData)

        $.ajax({
            type: "POST",
            url: '/api/teacher/insert',
            cache: false,
            data: reqData,
            dataType:'json',
            success:function (reqData){

                if(reqData == 2){
                    alert("저장되었습니다.");
                    location.href = "/teacher/list-view";

                } else {
                    alert("저장에 실패하였습니다.");
                    location.href = "/teacher/list-view";
                }
            }
        });
    }

    // ajaxCall('post','/api/teacher/insert', reqData, function (resData) {
    //     console.log(resData);
    // });
}