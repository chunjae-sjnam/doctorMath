package com.chunjae.doctormath.main.preclass;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface CustomExamMapper {
    public Map<String, Object> dragImage() throws Exception;
    public void updateLogoInfo() throws Exception;



}