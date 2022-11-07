package com.chunjae.doctormath.main.login.controller;

import com.chunjae.doctormath.main.login.dto.request.LoginReqDto;
import com.chunjae.doctormath.main.login.dto.response.LoginResDto;
import com.chunjae.doctormath.main.login.service.LoginUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/main/")
public class LoginUserRestController {

    private final LoginUserService loginUserService;

    /**
     * 로그인 정보 가져오기
     */
    @RequestMapping("get-login-info")
    public int getLogin(LoginReqDto loginReqDto, HttpSession httpSession) {
        log.info("getLogin loginReqDto ==> {}", loginReqDto);
        int loginCount = loginUserService.getLoginCount(loginReqDto);
        log.info("getLogin loginCount ==> {}", loginCount);
        if (loginCount == 1) {

            LoginResDto login = loginUserService.getLogin(loginReqDto);
            if (login.getUserId() != null || login.getName() != null) {
                httpSession.setAttribute("USER_ID", login.getUserId());
                httpSession.setAttribute("USER_NAME", login.getName());
            }
        }

        return loginCount;
    }


        /**
         * 로그아웃
         *
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
