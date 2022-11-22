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

    const reqData = {
         name : document.getElementById('name_i').value
        ,userId : document.getElementById('userId_i').value
        ,tell : document.getElementById('tell_i').value
    }
    console.log(reqData)

    ajaxCall('post','/api/teacher/insert', reqData, function (resData) {
        console.log(resData);
    });
}