package com.chunjae.doctormath.main.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("main")
public class LoginUserController {

    protected Logger logger = LoggerFactory.getLogger(LoginUserController.class);

    @Autowired
    private LoginUserService loginUserService;



    @RequestMapping("/login")
    public String index(HttpSession session, HttpServletRequest request, Model model) throws Exception{
        String result = "main/login/login";

        return result;
    }


    @RequestMapping(value = "/getLoginInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@RequestBody Map<String, Object> param, HttpSession httpSession) throws Exception {
        String userId = String.valueOf(param.getOrDefault("userId",""));
        String password = String.valueOf(param.getOrDefault("password",""));
        Map<String, Object> loginMap = loginUserService.login(param);
        //Map<String, Object> loginMap =new HashMap<>();
        loginMap.put("login", true);
        if (loginMap.get("login").equals(true)) {
            //User user = (User) loginMap.get("user");
            httpSession.setAttribute("USER_ID", loginMap.get("userid"));
            httpSession.setAttribute("USER_NAME", loginMap.get("name"));
        }
        return loginMap;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("USER_ID") != null) {
            httpSession.invalidate();
        }
        return "redirect:/main/login";
    }


    @RequestMapping("/join")
    public String join(HttpSession session, HttpServletRequest request, Model model) throws Exception{
        String result = "main/login/join";

        return result;
    }

    @RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
    @ResponseBody
    public String sendSMS(String phoneNumber){
        Random rand  = new Random();
        String numStr = "";

        for(int i=0; i<6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        System.out.println("수신자 번호 : " + phoneNumber);
        System.out.println("인증번호 : " + numStr);
        loginUserService.certifiedPhoneNumber(phoneNumber,numStr);
        return numStr;
    }

}
