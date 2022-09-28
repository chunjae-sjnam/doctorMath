package com.chunjae.doctormath.homePage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
    @Autowired
    private HomeMapper homeMapper;


    public int getIndexForTest() throws Exception{
        int test= homeMapper.getIndexForTest();
        return test;
    }

}
