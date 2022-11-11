// ajax 공통호출
const ajaxCall = (type, url, param, callback) => {
    $.ajax({
        url: url,
        type : type,
        data : param,
        success: function (data) {
            return callback(data);
        },
        error : function (xhr, status, error) {
            alert("관리자에게 문의 하세요.");
            console.log(xhr, status, error);
        }
    });
}