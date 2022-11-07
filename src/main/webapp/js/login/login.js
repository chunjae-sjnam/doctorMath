

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

        if (resData > 0) {
            location.href = "/main/preClass";
        } else {
            alert("계정을 확인해 주세요.")
        }


    })
}

function saveUserInfo(){

    if($('input:checkbox[id="box1"]').is(":checked") == true){
        document.getElementById("msgArea").innerText="저장 하시겠습니까?";
    }else{
        document.getElementById("msgArea").innerText="";
    }
}

function findId(){

}

function findPassword(){

}


document.addEventListener("DOMContentLoaded", function(){
    if(getCookie("id") != ""){
        document.getElementById("userId").value = getCookie("id");
        $('input:checkbox[id="box1"]').attr("checked", true);
    }else{
        $('input:checkbox[id="box1"]').attr("checked", false);
    }
});

function getCookie(cName) {
    cName = cName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cName);
    var cValue = '';
    if(start != -1){
        start += cName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cValue = cookieData.substring(start, end);
    }
    return unescape(cValue);
}

function setCookie(name,value,expireddays){
    const expiredDate = new Date();
    expiredDate.setDate(expiredDate.getDate() + expireddays);
    document.cookie = name + "=" + escape(value) + "; path=/; expires=" + expiredDate.toGMTString() + ";";
}

function enterKey(){
    if(window.event.keyCode == 13){
        login();
    }
}

function freeTrial(){

}

function katalkConsulting(){

}