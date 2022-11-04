package com.chunjae.doctormath.homepage.helpdesk;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HelpdeskMapper {
    public List<Map<String, Object>> faqList(Map<String, Object> param) throws Exception;

    public List<Map<String, Object>> noticeList(Map<String, Object> param) throws Exception;

    public int noticeListCount(Map<String, Object> param) throws Exception;

    public Map<String,Object> noticeDetail(Map<String, Object> param) throws Exception;

    public Map<String, Object> noticeAttach(Map<String, Object> param) throws Exception;

    public void insertInquiry() throws Exception;

    public List<Map<String, Object>> boardList(Map<String, Object> param) throws Exception;

    public int boardListCount(Map<String, Object> param) throws Exception;

    public Map<String,Object> boardDetail(Map<String, Object> param) throws Exception;

    public List<Map<String, Object>>  boardDetailList(Map<String, Object> param) throws Exception;

}