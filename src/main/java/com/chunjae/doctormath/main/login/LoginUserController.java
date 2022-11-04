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

/**
 * 메인>로그인
 */
@Controller
@RequestMapping("main")
public class LoginUserController {

    protected Logger logger = LoggerFactory.getLogger(LoginUserController.class);

    @Autowired
    private LoginUserService loginUserService;


    /**
     * 로그인
     * @param session
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String index(HttpSession session, HttpServletRequest request, Model model) throws Exception{
        String result = "main/login/login";

        return result;
    }


    /**
     * 로그인 정보 가져오기
     * @param param
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getLoginInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@RequestBody Map<String, Object> param, HttpSession httpSession) throws Exception {
        Map<String, Object> loginMap = loginUserService.login(param);
        if (loginMap.get("login").equals(true)) {
            httpSession.setAttribute("USER_ID", loginMap.get("userId"));
            httpSession.setAttribute("USER_NAME", loginMap.get("name"));

        }
        return loginMap;
    }

    /**
     * 로그아웃
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("USER_ID") != null) {
            httpSession.invalidate();
        }
        return "redirect:/main/login";
    }

}
