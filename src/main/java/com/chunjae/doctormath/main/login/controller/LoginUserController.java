package com.chunjae.doctormath.main.login.controller;

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
     */
    @RequestMapping("/login")
    public String loginView() {
        return "main/login/login";
    }

    /**
     * 임시 회원가입
     */
    @RequestMapping("/user-add-view")
    public String userAddView() {
        return "main/login/userAdd";
    }

}
