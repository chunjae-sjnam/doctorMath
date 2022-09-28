package com.chunjae.doctormath.homePage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) throws Exception{
        int testIndex=homeService.getIndexForTest();
        model.addAttribute("testIndex", String.valueOf(testIndex));
        return "index";
    }

}
