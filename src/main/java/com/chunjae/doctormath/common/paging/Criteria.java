package com.chunjae.doctormath.common.paging;

public class Criteria {
    //현재페이지
    private int pageNum;
    //페이지당 보이는 게시물 갯수
    private int numPerPage;
    //페이지가 처음 시작하는 row
    private long offset;

    /*검색 키워드*/
    private String keyword;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }



    public Criteria() {
        this(1, 5);
    }

    public Criteria(int pageNum, int numPerPage) {
        this.pageNum = pageNum;
        this.numPerPage = numPerPage;
        this.offset = (pageNum - 1) * numPerPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setpageNum(int pageNum) {
        if (pageNum <= 0) {
            this.pageNum = 1;
        } else {
            this.pageNum = pageNum;
        }

        this.pageNum = pageNum;
    }

    public int getnumPerPage() {
        return numPerPage;
    }

    public void setnumPerPage(int numPerPage) {
        if (numPerPage <= 0 || numPerPage > 30) {
            this.numPerPage = 30;
        } else {
            this.numPerPage = numPerPage;
        }

        this.numPerPage = numPerPage;
    }

    public int getStartPage() {
        return (this.pageNum - 1) * this.numPerPage + 1;
    }

    public long getOffset(){
        return this.offset;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "pageNum=" + pageNum +
                ", numPerPage=" + numPerPage +
                ", offset=" + offset +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
