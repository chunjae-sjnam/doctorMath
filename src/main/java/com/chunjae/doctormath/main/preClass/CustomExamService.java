package com.chunjae.doctormath.main.preClass;

//import com.chunjae.doctormath.main.login.LoginUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomExamService {

    @Autowired
    private CustomExamMapper customExamMapper;

    public Map<String, Object> dragImage() throws Exception{
        System.out.println("upload Service !!! ");
        Map<String, Object> resultMap =new HashMap<>();
        resultMap= customExamMapper.dragImage();

        System.out.println(resultMap);


        return resultMap;
    }

    public void updateLogoInfo() throws Exception{
        System.out.println("updateLogoInfo - Service 111 !!! ");
        Map<String, Object> resultMap =new HashMap<>();
        customExamMapper.updateLogoInfo();

        System.out.println("updateLogoInfo - Service 222 !!! ");
    }
}
