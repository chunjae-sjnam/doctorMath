package com.chunjae.doctormath.homePage.helpdesk;

//import com.chunjae.doctormath.main.preClass.CustomExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HelpdeskService {

    @Autowired
    private HelpdeskMapper helpdeskMapper;

    public Map<String, Object> faqList(Map<String, Object> param) throws Exception{
        System.out.println("start faq service- 111 !!! ");
        System.out.println("service- param: " + param);
        System.out.println(param.get("searchTxt"));

        Map<String, Object> resultMap =new HashMap<>();
        List<Map<String, Object>> list= helpdeskMapper.faqList(param);
        //System.out.println(list);
        resultMap.put("list", list);
        resultMap.put("searchTxt", param.get("searchTxt"));

        return resultMap;

    }

    public Map<String, Object> noticeList(Map<String, Object> param) throws Exception{
        System.out.println("start faq service- 111 !!! ");
        System.out.println("service- param: " + param);
        System.out.println(param.get("searchType"));

        Map<String, Object> resultMap =new HashMap<>();
        List<Map<String, Object>> list= helpdeskMapper.noticeList(param);

        System.out.println(list);
        System.out.println(param.get("searchTxt"));

        resultMap.put("list", list);
        resultMap.put("searchTxt", param.get("searchTxt"));
        resultMap.put("searchType", param.get("searchType"));

        return resultMap;

    }

    public Map<String, Object> noticeDetail(Map<String, Object> param) throws Exception{
        System.out.println("noticeDetail service- 111 !!! ");
        System.out.println("noticeDetail service- param: " + param);

        Map<String, Object> resultMap =new HashMap<>();
        Map<String, Object> list= helpdeskMapper.noticeDetail(param);
        //System.out.println(list);
        resultMap.put("list", list);
        resultMap.put("searchTxt", param.get("searchTxt"));

        return resultMap;

    }

}
