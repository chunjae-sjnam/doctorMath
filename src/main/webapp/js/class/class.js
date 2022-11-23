// 담당 선생님 검색
const mainTeacherList = () => {

    const reqData = {
        hakwonCode : "H0000005",
        teacherName : document.getElementById("mainTeacherName_i").value
    }

    $.ajax({
        type: "POST",
        url: '/api/class/class-search-teacher-list',
        data: reqData,
        success:function (resData){
            console.log(resData);
        }
    });
}

// 보조 선생님 검색
const subTeacherList = () => {

    const reqData = {
        hakwonCode : "H0000005",
        teacherName : document.getElementById("subTeacherName_i").value
    }

    $.ajax({
        type: "POST",
        url: '/api/class/class-search-teacher-list',
        data: reqData,
        success:function (resData){
            console.log(resData);
        }
    });
}

// 등록
const insertClass = () => {
    console.log("등록")

}