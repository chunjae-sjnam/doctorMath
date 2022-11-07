package com.chunjae.doctormath.main.login.controller;

import com.chunjae.doctormath.main.login.service.LoginUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 메인>로그인
 */
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("main")
public class LoginUserController {

    /**
     * 로그인
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "main/login/login";
    }


}
