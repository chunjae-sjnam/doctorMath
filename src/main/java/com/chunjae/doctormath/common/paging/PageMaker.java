package com.chunjae.doctormath.common.paging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

public class PageMaker {
    protected Logger logger = LoggerFactory.getLogger(PageMaker.class);

    private Criteria criteria;

    private int totalCount; // 전체 데이터 수
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;

    private int displayPageNum = 10;
    private int realEndPage;

    public Criteria getCriteria() {
        return criteria;
    }
    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        memberInit();
    }

    public void memberInit() {
        endPage = (int)(Math.ceil(criteria.getPageNum()/(double)displayPageNum)) * displayPageNum;

        startPage = endPage - displayPageNum + 1;

        realEndPage = (int)(Math.ceil(totalCount/(double) criteria.getnumPerPage()));

        if(endPage > realEndPage) {
            endPage = realEndPage;
        }

        prev = (startPage == 1 ? false : true);

        next = totalCount > (endPage * criteria.getnumPerPage()) ? true : false;
    }

    public String makeQuery(int page) {
        UriComponents uriComp = UriComponentsBuilder.newInstance()
                .queryParam("pageNum", page)
                .queryParam("numPerPage", criteria.getnumPerPage())
                .queryParam("keyword", this.getCriteria().getKeyword())
                .build();

        return uriComp.toString();
    }

    public Map<String,Integer> getPageInfo(){
        Map<String,Integer> map = new HashMap<>();
        map.put("current_page",this.criteria.getPageNum());
        map.put("total_elements",this.totalCount);
        map.put("total_page",this.realEndPage);
        if(this.prev){
            map.put("prev_page",this.getCriteria().getPageNum() -1);
        }
        if(this.next){
            map.put("next_page",this.getCriteria().getPageNum() +1);
        }
        return map;
    }

    public int getStartPage() {
        return startPage;
    }
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    public int getEndPage() {
        return endPage;
    }
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
    public boolean isPrev() {
        return prev;
    }
    public void setPrev(boolean prev) {
        this.prev = prev;
    }
    public boolean isNext() {
        return next;
    }
    public void setNext(boolean next) {
        this.next = next;
    }
    public int getDisplayPageNum() {
        return displayPageNum;
    }
    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }
    public int getRealEndPage() {
        return realEndPage;
    }
    public void setRealEndPage(int realEndPage) {
        this.realEndPage = realEndPage;
    }
}
