package com.chunjae.doctormath.main.login.service;

import com.chunjae.doctormath.main.login.dto.request.LoginReqDto;
import com.chunjae.doctormath.main.login.dto.response.LoginResDto;
import com.chunjae.doctormath.main.login.mapper.LoginUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 로그인 service
 */
@Service
@RequiredArgsConstructor
public class LoginUserService {

    private final LoginUserMapper loginUserMapper;

    /**
     * 로그인 정보 가져오기
     */
    public LoginResDto getLogin(LoginReqDto loginReqDto) {
        return loginUserMapper.getLogin(loginReqDto);
    }

    public int getLoginCount(LoginReqDto loginReqDto) {
        return loginUserMapper.getLoginCount(loginReqDto);
    }

    public int userAdd(LoginReqDto loginReqDto) {
        return loginUserMapper.userAdd(loginReqDto);
    }
}
