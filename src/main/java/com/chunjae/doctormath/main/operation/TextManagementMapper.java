package com.chunjae.doctormath.main.operation;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TextManagementMapper {

    public List<Map<String, Object>> selectreciverList(Map<String, Object> inputs) throws Exception;

    public List<Map<String, Object>> selectHisSent(Map<String, Object> inputs) throws Exception;

    public Integer templateCnt(Map<String, Object> inputs) throws Exception;

    public List<Map<String, Object>> getTemplateListWithMark(Map<String, Object> inputs) throws Exception;

    public int insertToTbSMSTemplate(Map<String, Object> inputs) throws Exception;

    public List<Map<String, Object>> getClassList(Map<String, Object> inputs) throws Exception;

    public List<Map<String, Object>> getTemplateList(Map<String, Object> inputs) throws Exception;






}
