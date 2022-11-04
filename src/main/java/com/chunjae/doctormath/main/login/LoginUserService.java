package com.chunjae.doctormath.main.login;

import com.chunjae.doctormath.homePage.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 로그인 service
 */
@Service
public class LoginUserService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    /**
     * 로그인 정보 가져오기
     * @param user
     * @return
     * @throws Exception
     */
    public Map<String, Object> login(Map<String, Object> user) throws Exception{
        Map<String, Object> resultMap =new HashMap<>();
        resultMap= loginUserMapper.login(user);
        resultMap.put("login", true);

        return resultMap;
    }
}
