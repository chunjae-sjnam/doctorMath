package com.chunjae.doctormath.main.login;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LoginUserMapper {

    public Map<String, Object> login(Map<String, Object> inputs) throws Exception;
}
