package com.chunjae.doctormath.homePage;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeMapper {

    public int getIndexForTest() throws Exception;
}
