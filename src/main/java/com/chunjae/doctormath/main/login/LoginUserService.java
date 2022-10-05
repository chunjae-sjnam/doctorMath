package com.chunjae.doctormath.main.login;

import com.chunjae.doctormath.homePage.HomeMapper;
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
}
