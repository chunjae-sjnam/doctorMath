package com.chunjae.doctormath.homepage.auth;

import com.chunjae.doctormath.homepage.helpdesk.HelpdeskMapper;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import net.nurigo.java_sdk.api.Message;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private HelpdeskMapper helpdeskMapper;

    //public static void certifiedPhoneNumber(String phoneNumber, String cerNum) {
    /*
     * 문자인증서비스
     */
    public static String certifiedPhoneNumber(String phoneNumber, String cerNum) {
        // test CoolSms
        String api_key = "NCST8GPA6LZSJTYL";
        String api_secret = "7UXELRJD0KCEEDEOWEXWUQ4D0T9PQX4R";
        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber); // 수신전화번호
        params.put("from", "010-5105-9047"); //test
        params.put("type", "SMS");
        params.put("text", "휴대폰인증 테스트 메시지 : 인증번호는" + "["+cerNum+"]" + "입니다.");
        params.put("app_version", "doctorMath test app 1.2");

        try {
            JSONObject obj;
            obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

        System.out.println("Auth Service 인증번호 : " + cerNum);

        return cerNum;

    }
}
