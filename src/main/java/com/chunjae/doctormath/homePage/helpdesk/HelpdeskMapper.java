package com.chunjae.doctormath.homePage.helpdesk;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HelpdeskMapper {

    public List<Map<String, Object>> faqList(Map<String, Object> param) throws Exception;

    public List<Map<String, Object>> noticeList(Map<String, Object> param) throws Exception;
}