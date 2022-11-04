package com.chunjae.doctormath.main.preclass;


import com.chunjae.doctormath.common.MathImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * 수업준비
 * 맞춤 문제지Controller
 */
@Controller
@RequestMapping("main")
public class CustomExamController {

    protected Logger logger = LoggerFactory.getLogger(CustomExamController.class);


    @RequestMapping("/preClass")
    public String index(HttpSession session, HttpServletRequest request, Model model) throws Exception {

        if (session.getAttribute("USER_ID") == null) {
            return "redirect:main/login";
        }

        String result = "main/preClass/customExam";

        return result;
    }



    @GetMapping("/examDownloadFile2")
    public String testRenderHtmlnPng(@RequestParam Map<String, Object> param, HttpServletRequest req)throws  Exception{

        String testMath = new String();
        testMath="1. 연립방정식\\( \\left\\{\\begin{array}{l}3 x-4 y=6 \\\\ -x-3 y=5+a\\end{array}\\right. \\)를 만족시키는 x의 값이 y값의 2배일 때, a의 값은?<br><br>\n" +
                "\n" +
                "① -2 <br>\n" +
                "② -1 <br>\n" +
                "③ 0 <br>\n" +
                "④ 1 <br>\n" +
                "⑤ 2\n" +
                "\n" +
                "\n" +
                "<br><br><br>\n" +
                "\n" +
                "2. 연립방정식 \\( \\left\\{\\begin{array}{l}0.4 x+0.3 y=3 \\\\ \\frac{x}{3}+\\frac{y-8}{6}=1\\end{array}\\right. \\) 를 풀면?<br><br>\n" +
                "\n" +
                "① \\( x=4, y=7 \\)<br>\n" +
                "② \\( x=2, y=5 \\)<br>\n" +
                "③ \\( x=8, y=14 \\)<br>\n" +
                "④ \\( x=6, y=10 \\)<br>\n" +
                "⑤ \\( x=6, y=2 \\)\n" +
                "\n" +
                "<br><br><br>\n" +
                "\n" +
                "3. 분수 \\( \\frac{4}{11} \\)를 소수로 나타내었을 때, 소수점 아래 30번째 자리까지의 숫자의 합을 구하면?  <br><br>\n" +
                "\n" +
                "① 9<br>\n" +
                "② 15<br>\n" +
                "③ 36<br>\n" +
                "④ 135<br>\n" +
                "⑤ 145\n" +
                "\n" +
                "<br><br><br>\n" +
                "\n" +
                "4. 연립방정식 \\( \\left\\{\\begin{array}{l}\\frac{x}{3}+\\frac{1}{2}(x+y)=2 \\\\ 0.3 x-0.1 y=1\\end{array}\\right. \\) 의 해는? <br><br>\n" +
                "① \\( x=3, y=-1 \\) <br>\n" +
                "② \\( x=3, y=1 \\) <br>\n" +
                "③ \\( x=1, y=3 \\) <br>\n" +
                "④ \\( x=1, y=-3 \\) <br>\n" +
                "⑤ \\( x=-3, y=1 \\)\n" +
                "\n" +
                "<br><br><br>\n" +
                "\n" +
                "5. \\( a=3^{x+1}, b=\\frac{5^{x+1}}{2} \\)일 때, \\( \\left(\\frac{9}{5}\\right)^{x} \\)을 \\( a \\)와 \\( b \\)의 식으로 옳게 나타낸 것은?  <br><br>\n" +
                "\n" +
                "① \\( \\frac{9 a^{2}}{5b} \\)  <br>\n" +
                "② \\( \\frac{35 a^{2}}{3b} \\)  <br>\n" +
                "③ \\( \\frac{5 a^{2}}{18 b} \\) <br>\n" +
                "④ \\( \\frac{18 a^{2}}{25 b^{2}} \\) <br>\n" +
                "⑤ \\( \\frac{25 a^{2}}{36 b^{2}} \\)";

         Map<String, String> testImage=MathImageUtil.renderHtmlToPng("testImage", testMath);

         System.out.println("testresult : " + testImage.get("imagePath"));

        String result = "main/preClass/customExam";
        //return "redirect:/views/indexTest7.jsp";
        return result;

    }


}
