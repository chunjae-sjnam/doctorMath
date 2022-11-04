package com.chunjae.doctormath.main.login;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginUserService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    public Map<String, Object> login(Map<String, Object> user) throws Exception{
        Map<String, Object> resultMap =new HashMap<>();
        resultMap= loginUserMapper.login(user);
        return resultMap;
    }

    public void certifiedPhoneNumber(String phoneNumber, String cerNum) {

        String api_key = "NCSCE1KH7P3UCMOG";
        String api_secret = "79RI8262CNB7VZ9BGLTYSKOXUYEQQJZ4";
        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);          // 수신전화번호
        params.put("from", "01036791923");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "천재교과서 인증번호 : " + cerNum);
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }

}
