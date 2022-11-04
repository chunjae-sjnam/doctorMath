package com.chunjae.doctormath.homepage.helpdesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class HelpdeskService {

    @Autowired
    private HelpdeskMapper helpdeskMapper;

    /*
    * FAQ 목록
    * searchTxt: 검섹어
    */
    public Map<String, Object> faqList(Map<String, Object> param) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> list = helpdeskMapper.faqList(param);

        resultMap.put("list", list);
        resultMap.put("searchTxt", param.get("searchTxt"));

        return resultMap;

    }

    /*
     * 공지사항 목록
     * searchTxt: 검섹어
     */
    public Map<String, Object> noticeList(Map<String, Object> param) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> list = helpdeskMapper.noticeList(param);

        resultMap.put("list", list);
        resultMap.put("searchTxt", param.get("searchTxt"));
        resultMap.put("searchType", param.get("searchType"));

        return resultMap;
    }

    /*
     * 공지사항 전체 갯수
     * 페이징처리시 사용 
     */
    public int noticeListCount(Map<String, Object> param) throws Exception {
        return helpdeskMapper.noticeListCount(param);
    }

    /*
     * 공지사항 상세 조회
     */
    public Map<String, Object> noticeDetail(Map<String, Object> param) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> list = helpdeskMapper.noticeDetail(param);
        
        resultMap.put("list", list);
        resultMap.put("searchTxt", param.get("searchTxt"));

        return resultMap;

    }

    /*
     * 공지사항 - 첨부파일 조회
     */
    public Map<String, Object> noticeAttach(Map<String, Object> param) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> attachInfo = helpdeskMapper.noticeAttach(param);
        resultMap.put("attachInfo", attachInfo);
        resultMap.put("searchTxt", param.get("searchTxt"));

        return resultMap;
    }

    /*
     * 1:1 문의사항 - 글등록
     */
    public void insertInquiry() throws Exception {
        
        helpdeskMapper.insertInquiry();
    }

    /*
     * 사용후기 / 업데이트소식
     */
    public Map<String, Object> boardList(Map<String, Object> param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> list = helpdeskMapper.boardList(param);
        resultMap.put("list", list);

        return resultMap;
    }

    /*
     * 사용후기 / 업데이트소식
     * 전체카운트 - 페이징 처리시 사용
     */
    public int boardListCount(Map<String, Object> param) throws Exception {

        return helpdeskMapper.boardListCount(param);
    }

    /*
     * 사용후기 / 업데이트 소식 상세
     */
    public Map<String, Object> boardDetail(Map<String, Object> param) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> list = helpdeskMapper.boardDetail(param);
        return resultMap;

    }
    /*
     * 사용후기 및 업데이트소식 - 연관배열 사용예정 (test)
     */
    public Map<String, Object> boardDetailList(Map<String, Object> param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> list = helpdeskMapper.boardDetailList(param);

        System.out.println(list);
        return resultMap;
    }
}
