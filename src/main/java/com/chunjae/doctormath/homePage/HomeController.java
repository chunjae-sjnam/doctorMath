package com.chunjae.doctormath.homePage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    protected static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomeService homeService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) throws Exception {
        int testIndex = homeService.getIndexForTest();
        model.addAttribute("testIndex", String.valueOf(testIndex));
        return "index";
    }

}
