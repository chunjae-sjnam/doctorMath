
// 로그인
const login = () => {
    const userId = document.getElementById("userId").value;
    const password = document.getElementById("password").value;
    const isChecked = document.getElementById('box1').checked ? 'Y' : 'N';

    const reqData = {
        userId : userId,
        password : password,
        saveLogin : isChecked
    };
    console.log(reqData)

    ajaxCall('post','/api/main/get-login-info', reqData, function (resData) {

        if (resData === 1) {
            location.href = "/main/preClass";
        } else {
            alert("계정을 확인해 주세요.")
        }
    })
}
