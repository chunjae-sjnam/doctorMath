package com.chunjae.doctormath.main.login.mapper;

import com.chunjae.doctormath.main.login.dto.request.LoginReqDto;
import com.chunjae.doctormath.main.login.dto.response.LoginResDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginUserMapper {

    LoginResDto getLogin(LoginReqDto loginReqDto);

    int getLoginCount(LoginReqDto loginReqDto);

    int userAdd(LoginReqDto loginReqDto);
}
