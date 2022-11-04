<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <script type="text/javascript" src="../../../js/jquery-3.6.1.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <title>사용자 회원가입 화면</title>
</head>
<body>
<div class="wrap join">
    <div class="user-table">
        <table>
            <p>개인정보</p>
            <tbody>
            <tr>
                <th>이름 <em>*</em></th>
                <td>
                    <input type="text" name="web_user_name" class="ko" placeholder="한글만 입력 가능">
                </td>
            </tr>
            <tr>
                <th>아이디 <em>*</em></th>
                <td>
                    <div class="btn-form">
                        <input type="text" class="user_id" name="web_user_id" placeholder="영문자+숫자 조합 10자리 이하" maxlength="10">
                        <button type="button" onclick="duplicateIdChk(this);">중복 확인</button>
                        <em class="id-check" style="display:none;"><i>＊</i>사용가능한 아이디입니다. </em>
                    </div>
                </td>
            </tr>
            <tr>
                <th>비밀번호 <em>*</em></th>
                <td>
                    <input type="password" name="web_user_password" placeholder="8~16자리, 문자, 숫자, 특수문자" maxlength="16" class="password_action">
                </td>
            </tr>
            <tr>
                <th>비밀번호 재확인 <em>*</em></th>
                <td>
                    <input type="password" name="web_user_password_re" placeholder="8~16자리, 문자, 숫자, 특수문자" maxlength="16" class="password_action">
                    <em class="error red passwordFalse" style="display:none;"><i>＊</i>비밀번호가 일치하지 않습니다.</em>
                </td>
            </tr>
            <tr>
                <th>휴대폰번호 <em>*</em></th>
                <td>
                    <span class="select-box">
                        <select class="phone" name="phone" id="txtNumber1">
                            <option value="010">010</option>
                            <option value="011">011</option>
                            <option value="016">016</option>
                            <option value="017">017</option>
                            <option value="018">018</option>
                            <option value="019">019</option>
                        </select>
                    </span>
                    <input type="text" name="web_mobile" id="txtNumber2" class="number" maxlength="4" style="width: 50px">
                    <input type="text" name="web_mobile" id="txtNumber3" class="number" maxlength="4" style="width: 50px">


                    <button type="button" onclick="fn_number(this);">인증번호 요청</button>
                </td>
            </tr>
            <tr>
                <th>인증번호 <em>*</em></th>
                <td>
                    <div class="btn-form authDiv">
                        <input type="text" class="mobile" name="phone_response_auth" id="inputCertifiedNumber" maxlength="6">
                        <button type="button" id="checkBtn">인증 확인</button>
                        <small class="confirm1" style="display:none;">인증되었습니다.</small>
                        <small class="confirm2" style="display:none;">인증번호가 정확하지 않습니다. 다시 확인해 주세요.</small>
                    </div>
                    <span style="color: white" class="newPw"></span>
                </td>
            </tr>
            <tr>
                <th>이메일 주소 <em>*</em></th>
                <td class="email">
                    <input type="text" class="subWeb" name="email_sub">
                    <span>@</span>
                    <span class="select-box">
                        <select class="emailDomain" name="email_domain" id="">
                            <option value="direct">직접 입력</option>
                            <option value="daum.net">daum.net</option>
                            <option value="naver.com">naver.com</option>
                        </select>
                    </span>
                    <input type="text" class="directWeb" name="email_direct">
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="hakwon-table">
        <table>
            <p>학원정보</p>
            <tbody>
            <tr>
                <th>학원명 <em>*</em></th>
                <td>
                    <input type="text" name="web_hakwon_name" class="ko">
                </td>
            </tr>
            <tr>
                <th>학원,공부방 주소 <em>*</em></th>
                <td>
                    <div>
                        <input type="text" id="zipcode" placeholder="우편번호" maxlength="6">
                        <button type="button" onclick="fn_zipcode();">우편번호 찾기</button>
                    </div>
                    <div>
                        <input type="text" id="address" placeholder="주소">
                    </div>
                    <div>
                        <input type="text" id="detailAddress" placeholder="상세주소">
                    </div>
                </td>
            </tr>
            <tr>
                <th>정보 취득 매체</th>
                <td>
                    <span class="select-box">
                        <select>
                            <option value="direct">선택</option>
                            <option value="">지역센터 소개</option>
                            <option value="">블로그</option>
                            <option value="">페이스북</option>
                            <option value="">앤써통</option>
                            <option value="">학관노</option>
                            <option value="">인터넷검색</option>
                            <option value="">신문기사</option>
                            <option value="">지인소개</option>
                        </select>
                    </span>
                </td>
            </tr>
            <tr>
                <th>SNS/이메일 수신 여부</th>
                <td>
                    <input type="radio" name="chk_info" value="">수신
                    <input type="radio" name="chk_info" value="">수신 안함
                    <small style="color: gray">ID/PW 찾기 등 운영에 필요한 중요정보는 동의여부 상관없이 발송 됩니다.</small>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script>
    //인증번호 요청
    function fn_number() {

        const inputPhoneNumber = document.getElementById("txtNumber1").value
            + document.getElementById("txtNumber2").value
            + document.getElementById("txtNumber3").value;
        const phoneNumber = inputPhoneNumber;
        alert('인증번호 발송 완료!');

        $.ajax({
            type: "POST",
            url: '/main/sendSMS',
            data: {
                "phoneNumber" : phoneNumber
            },
            success: function(res){

                //인증확인
                $('#checkBtn').click(function(){
                    $('.confirm1').attr("style", "display:none;");
                    $('.confirm2').attr("style", "display:none;");

                    if($.trim(res) ==$('#inputCertifiedNumber').val()){
                        $('.confirm1').attr("style", "display:inline;");
                        $('.confirm1').attr("style", "color:red;");

                        $.ajax({
                            type: "GET",
                            url: "/update/phone",
                            data: {
                                "phoneNumber" : $('#inputPhoneNumber').val()
                            }
                        })
                        // document.location.href="/";
                    }else{
                        $('.confirm2').attr("style", "display:inline;");
                        $('.confirm2').attr("style", "color:red;");
                    }
                })
            }
        });
    }

    //우편번호 찾기
    function fn_zipcode() {

        new daum.Postcode({
            oncomplete: function(data) {
                var zipcode = "";
                var addr = "";

                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                document.getElementById("zipcode").value = data.zonecode;
                document.getElementById("address").value = addr;
            }
        }).open();
    }
</script>
</body>
</html>