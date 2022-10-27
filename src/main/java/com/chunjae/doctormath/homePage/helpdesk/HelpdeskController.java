package com.chunjae.doctormath.homePage.helpdesk;

import com.chunjae.doctormath.main.preClass.CustomExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aspose.tex.*;

import java.util.Map;
import org.springframework.ui.Model;

@Controller
@RequestMapping("helpdesk")
public class HelpdeskController {

    @Autowired
    private HelpdeskService helpdeskService;

    @RequestMapping("/faq")
    public String faq(HttpServletRequest request, @RequestParam Map<String, Object> param, Model model) throws Exception {
        //System.out.println("start faq - 111 !!! ");
        //System.out.println("controller param: " + param);
        System.out.println(request.getParameter("searchText"));

        param.put("searchTxt", request.getParameter("searchText"));
        Map<String, Object> faqmap = helpdeskService.faqList(param);

        System.out.println(faqmap);
        model.addAttribute("data", faqmap);
        model.addAttribute("searchTxt", request.getParameter("searchText"));

        String result = "homePage/helpdesk/faq";

        return result;
    }

    @RequestMapping("/notice")
    public String notice(HttpServletRequest request, @RequestParam Map<String, Object> param, Model model) throws Exception {
        //System.out.println("start faq - 111 !!! ");
        //System.out.println("controller param: " + param);
        System.out.println(request.getParameter("searchText"));

        param.put("searchTxt", request.getParameter("searchText"));
        Map<String, Object> noticemap = helpdeskService.noticeList(param);

        System.out.println(noticemap);
        model.addAttribute("data", noticemap);
        model.addAttribute("searchTxt", request.getParameter("searchText"));

        String result = "homePage/helpdesk/notice";

        return result;
    }

}
