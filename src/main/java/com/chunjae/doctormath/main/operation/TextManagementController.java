package com.chunjae.doctormath.main.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 운영관리>문자관리
 */
@Controller
@RequestMapping("main")
public class TextManagementController {

    protected Logger logger = LoggerFactory.getLogger(TextManagementController.class);

    @Autowired
    private TextManagementService textManagementService;

    /**
     * 문자 전송 화면
     * @param param
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/sendText")
    public String init(@RequestParam Map<String, Object> param, HttpSession session, Model model) throws Exception {
        param.put("userId", session.getAttribute("USER_ID"));
        //param.put("hakwonCode", session.getAttribute("hakwonCode"));
        //param.put("teacherCode", session.getAttribute("teacherCode"));

        //temp
        param.put("hakwonCode","H0000413");
        param.put("teacherCode", "T0000081");

        Map<String, Object> data=textManagementService.selectHisSent(param);
        model.addAttribute("data", data);


        return "main/operation/textSend";
    }

    /**
     * 팝업>트리(클래스) 정보 가져오기 
     * @param param
     * @param session
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/getTreeClassList")
    public String getTreeClassList(@RequestParam Map<String, Object> param, HttpSession session, Model model) throws Exception {
        param.put("userId", session.getAttribute("USER_ID"));
        //param.put("hakwonCode", session.getAttribute("hakwonCode"));
        //param.put("teacherCode", session.getAttribute("teacherCode"));

        //temp
        param.put("hakwonCode","H0000413");
        param.put("teacherCode", "T0000081");

        //Map<String, Object> data=;
        model.addAttribute("tree", textManagementService.getTreeClassList(param));


        return "main/operation/treeClass";
    }

    @RequestMapping("/getTemplateList")
    public String getTemplateList(@RequestParam Map<String, Object> param, HttpSession session, Model model) throws Exception {
        param.put("userId", session.getAttribute("USER_ID"));
        //param.put("hakwonCode", session.getAttribute("hakwonCode"));
        //param.put("teacherCode", session.getAttribute("teacherCode"));

        //temp
        param.put("hakwonCode","H0000413");
        param.put("teacherCode", "T0000081");

        //Map<String, Object> data=;
        model.addAttribute("data", textManagementService.selectTemplates(param));


        return "main/operation/smsTemplateList";
    }

}
