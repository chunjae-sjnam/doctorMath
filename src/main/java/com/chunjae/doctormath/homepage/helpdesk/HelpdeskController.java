package com.chunjae.doctormath.homepage.helpdesk;

import com.chunjae.doctormath.common.StringUtil;
import com.chunjae.doctormath.common.paging.Criteria;
import com.chunjae.doctormath.common.paging.PageMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("helpdesk")
public class HelpdeskController {
    @Autowired
    private HelpdeskService helpdeskService;

    @RequestMapping("/index")
    public String index() throws Exception {
        return "homePage/helpdesk/index";
    }

    @RequestMapping("/join")
    public String join(HttpServletRequest request, @RequestParam Map<String, Object> param, Model model) throws Exception {
        System.out.println("controller param: " + param);

        String result = "homePage/helpdesk/join";

        return result;
    }
    @RequestMapping("/board")
    public String board(HttpServletRequest request, @RequestParam Map<String, Object> param, Model model, Criteria criteria) throws Exception {
        System.out.println("controller board: " + param);

        // 페이징처리
        PageMaker paging = new PageMaker();
        criteria.setnumPerPage(4);
        paging.setCriteria(criteria);
        paging.setTotalCount(helpdeskService.boardListCount(param));

        String pageNum = StringUtil.stringNullChg(param.get("pageNum"), "1");
        int startPage = paging.getCriteria().getStartPage();
        int numPerPage = paging.getCriteria().getnumPerPage();

        param.put("pageNum", (Integer.parseInt(pageNum) - 1) * numPerPage);
        param.put("criteria", criteria);
        // 리뷰목록
        Map<String, Object> boardmap = helpdeskService.boardList(param);

        model.addAttribute("data", boardmap);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("startPage", startPage);
        model.addAttribute("paging", paging);

        return "homePage/helpdesk/board";
    }

    @RequestMapping("/boardDetail")
    public String reviewDetail(HttpServletRequest request, @RequestParam Map<String, Object> param, Model model) throws Exception {
        Map<String, Object> reviewDetailmapTest = helpdeskService.boardDetailList(param);

        System.out.println("TEST: " + reviewDetailmapTest);

        return "homePage/helpdesk/boardDetail";
    }

    /*
     * FAQ 목록
     * searchTxt: 검섹어
     */
    @RequestMapping("/faq")
    public String faq(HttpServletRequest request, @RequestParam Map<String, Object> param, Model model) throws Exception {

        param.put("searchTxt", request.getParameter("searchText"));
        Map<String, Object> faqmap = helpdeskService.faqList(param);

        model.addAttribute("data", faqmap);
        model.addAttribute("searchTxt", request.getParameter("searchText"));

        return "homePage/helpdesk/faq";
    }

    /*
     * 공지사항 목록
     * searchTxt: 검섹어
     */
    @RequestMapping("/notice")
    public String notice(HttpServletRequest request, @RequestParam Map<String, Object> param, Model model, Criteria criteria) throws Exception {

        // 파라미터 관련
        param.put("searchTxt", request.getParameter("searchText"));

        // 페이징처리
        PageMaker paging = new PageMaker();
        criteria.setnumPerPage(5);
        paging.setCriteria(criteria);
        paging.setTotalCount(helpdeskService.noticeListCount(param));
        String pageNum = StringUtil.stringNullChg(param.get("pageNum"), "1");
        int startPage = paging.getCriteria().getStartPage();
        int numPerPage = paging.getCriteria().getnumPerPage();

        param.put("pageNum", (Integer.parseInt(pageNum) - 1) * numPerPage);
        param.put("criteria", criteria);
        Map<String, Object> noticemap = helpdeskService.noticeList(param);

        model.addAttribute("data", noticemap);
        model.addAttribute("searchTxt", request.getParameter("searchText"));
        model.addAttribute("searchType", request.getParameter("searchType"));

        model.addAttribute("pageNum", pageNum);
        model.addAttribute("startPage", startPage);
        model.addAttribute("paging", paging);
        model.addAttribute("listCount", helpdeskService.noticeListCount(param));

        return "homePage/helpdesk/notice";
    }


    /*
     * 공지사항 상세 조회
     */
    @RequestMapping("/noticeDetail")
    public String noticeDetail(HttpServletRequest request, @RequestParam Map<String, Object> param, Model model) throws Exception {

        Map<String, Object> noticemap = helpdeskService.noticeDetail(param);
        Map<String, Object> attachInfo = helpdeskService.noticeAttach(param);

        model.addAttribute("data", noticemap);
        model.addAttribute("searchTxt", request.getParameter("searchText"));
        model.addAttribute("data2", attachInfo);

        return "homePage/helpdesk/noticeDetail";
    }

    /*
     * 1:1 문의사항 - form 
     */
    @RequestMapping("/inquiry")
    public String inquiry(HttpServletRequest request, @RequestParam Map<String, Object> param, Model model) throws Exception {

        return "homePage/helpdesk/inquiry";
    }

    /*
     * 1:1 문의사항 - 게시글 등록
     */
    @RequestMapping(value = "/inquiryWrite", method = RequestMethod.POST)
    public void inquiryWrite(HttpServletRequest request, @RequestParam Map<String, Object> param, HttpServletResponse response) throws Exception {
        helpdeskService.insertInquiry();

        StringUtil.alertAndMovePage(response, "등록되었습니다.", "/helpdesk/inquiry");
    }
}
