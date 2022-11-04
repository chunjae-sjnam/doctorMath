package com.chunjae.doctormath.homePage.helpdesk;

import java.util.HashMap;
import java.util.Random;

import com.chunjae.doctormath.common.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("auth") //
public class AuthController {
    @RequestMapping(value = "/SendAuthMessage", method = RequestMethod.POST)
    //public void SendAuthMessage(HttpServletResponse response, Model model) throws Exception {
    public void SendAuthMessage(HttpServletResponse response, Model model) throws Exception {
        System.out.println("SendAuthMessage - !!! ");

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        System.out.println("numStr - !!! " + numStr);
        String phoneNumber = "01051059047";
        System.out.println("수신자 번호 : " + phoneNumber);
        System.out.println("인증번호 : " + numStr);
        String certNum = AuthService.certifiedPhoneNumber(phoneNumber,numStr);

        response.getWriter().print(certNum);
    }

    public class HelloData {
        private String certNum;
        private  String randomNum;

        public void setcertNum(String certNum) {
        }

        public void setrandomNum(String numStr) {
        }
    }
}
