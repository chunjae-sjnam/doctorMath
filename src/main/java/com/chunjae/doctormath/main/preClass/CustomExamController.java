package com.chunjae.doctormath.main.preClass;

import com.aspose.tex.rendering.ImageDevice;
import com.aspose.tex.rendering.PngSaveOptions;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aspose.tex.*;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;



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


        // test latex 이미지화
        testLatextoPng();


        String result = "main/preClass/customExam";

        return result;
    }

    public void testLatextoPng() throws Exception {
        //1. render html to text
        String htmlString = "" + "<div><h1>예제입니다.</h1><span>Convert HTML to Text</span><br>\n" +

                "                (3) \\( \\frac{15}{32}=\\frac{2}{32} \\)" + "                (4) \\( \\frac{7}{16} \\)" +

                "<div></div>";

        String html22 = "<div><h1>예제입니다.</h1><span>Convert HTML to Text</span>";

        String testHtml = "<span id=\"MathJax-Element-245-Frame\" class=\"mjx-chtml MathJax_CHTML\" tabindex=\"0\" data-mathml=\"<math xmlns=\\http://www.w3.org/1998/Math/MathML&quot;><munderover><mo movablelimits=&quot;false&quot;>&amp;#x2211;</mo><mrow class=&quot;MJX-TeXAtom-ORD&quot;><mi>n</mi><mo>=</mo><mn>1</mn></mrow><mrow class=&quot;MJX-TeXAtom-ORD&quot;><mn>10</mn></mrow></munderover><mtable rowspacing=&quot;4pt&quot; columnspacing=&quot;1em&quot;><mtr><mtd><mn>1</mn></mtd></mtr><mtr><mtd><mrow class=&quot;MJX-TeXAtom-ORD&quot;><mrow class=&quot;MJX-TeXAtom-ORD&quot;><mover><mrow><mspace width=&quot;thinmathspace&quot; /><msup><mrow class=&quot;MJX-TeXAtom-ORD&quot;><mrow><mo>{</mo><mi>f</mi><mrow><mo>(</mo><mi>n</mi><mo>)</mo></mrow><mo>}</mo></mrow></mrow><mrow class=&quot;MJX-TeXAtom-ORD&quot;><mn>2</mn></mrow></msup></mrow><mo stretchy=&quot;false&quot;>&amp;#x00AF;</mo></mover></mrow></mrow></mtd></mtr></mtable><mspace width=&quot;thinmathspace&quot; /></math>\" role=\"presentation\" style=\"font-size: 115%; position: relative;\"><span id=\"MJXc-Node-3259\" class=\"mjx-math\" aria-hidden=\"true\"><span id=\"MJXc-Node-3260\" class=\"mjx-mrow\"><span id=\"MJXc-Node-3261\" class=\"mjx-munderover\"><span class=\"mjx-itable\"><span class=\"mjx-row\"><span class=\"mjx-cell\"><span class=\"mjx-stack\"><span class=\"mjx-over\" style=\"font-size: 70.7%; padding-bottom: 0.236em; padding-top: 0.141em; padding-left: 0.439em;\"><span id=\"MJXc-Node-3268\" class=\"mjx-texatom\" style=\"\"><span id=\"MJXc-Node-3269\" class=\"mjx-mrow\"><span id=\"MJXc-Node-3270\" class=\"mjx-mn\"><span class=\"mjx-char MJXc-TeX-main-R\" style=\"padding-top: 0.383em; padding-bottom: 0.383em;\">10</span></span></span></span></span><span class=\"mjx-op\" style=\"padding-left: 0.136em;\"><span id=\"MJXc-Node-3262\" class=\"mjx-mo\"><span class=\"mjx-char MJXc-TeX-size1-R\" style=\"padding-top: 0.546em; padding-bottom: 0.546em;\">∑</span></span></span></span></span></span><span class=\"mjx-row\"><span class=\"mjx-under\" style=\"font-size: 70.7%; padding-top: 0.236em; padding-bottom: 0.141em;\"><span id=\"MJXc-Node-3263\" class=\"mjx-texatom\" style=\"\"><span id=\"MJXc-Node-3264\" class=\"mjx-mrow\"><span id=\"MJXc-Node-3265\" class=\"mjx-mi\"><span class=\"mjx-char MJXc-TeX-math-I\" style=\"padding-top: 0.22em; padding-bottom: 0.274em;\">n</span></span><span id=\"MJXc-Node-3266\" class=\"mjx-mo\"><span class=\"mjx-char MJXc-TeX-main-R\" style=\"padding-top: 0.057em; padding-bottom: 0.329em;\">=</span></span><span id=\"MJXc-Node-3267\" class=\"mjx-mn\"><span class=\"mjx-char MJXc-TeX-main-R\" style=\"padding-top: 0.383em; padding-bottom: 0.329em;\">1</span></span></span></span></span></span></span></span><span id=\"MJXc-Node-3271\" class=\"mjx-mtable MJXc-space1 MJXc-space1\" style=\"vertical-align: -1.161em; padding: 0px 0.167em;\"><span class=\"mjx-table\"><span id=\"MJXc-Node-3272\" class=\"mjx-mtr\" style=\"height: 1.2em;\"><span id=\"MJXc-Node-3273\" class=\"mjx-mtd\" style=\"padding: 0px; width: 3.65em;\"><span id=\"MJXc-Node-3274\" class=\"mjx-mrow\" style=\"margin-top: -0.2em;\"><span id=\"MJXc-Node-3275\" class=\"mjx-mn\"><span class=\"mjx-char MJXc-TeX-main-R\" style=\"padding-top: 0.383em; padding-bottom: 0.329em;\">1</span></span><span class=\"mjx-strut\"></span></span></span></span><span id=\"MJXc-Node-3276\" class=\"mjx-mtr\" style=\"height: 1.622em;\"><span id=\"MJXc-Node-3277\" class=\"mjx-mtd\" style=\"padding: 0.2em 0px 0px;\"><span id=\"MJXc-Node-3278\" class=\"mjx-mrow\"><span id=\"MJXc-Node-3279\" class=\"mjx-texatom\"><span id=\"MJXc-Node-3280\" class=\"mjx-mrow\"><span id=\"MJXc-Node-3281\" class=\"mjx-texatom\"><span id=\"MJXc-Node-3282\" class=\"mjx-mrow\"><span id=\"MJXc-Node-3283\" class=\"mjx-munderover\"><span class=\"mjx-stack\"><span class=\"mjx-over\" style=\"height: 0.096em; padding-bottom: 0.06em; padding-left: 1.575em;\"><span id=\"MJXc-Node-3300\" class=\"mjx-mo\" style=\"vertical-align: top;\"><span class=\"mjx-char MJXc-TeX-main-R\" style=\"padding-top: 0.329em; padding-bottom: 0.329em;\">¯</span></span></span><span class=\"mjx-op\"><span id=\"MJXc-Node-3284\" class=\"mjx-mrow\"><span id=\"MJXc-Node-3285\" class=\"mjx-mspace\" style=\"width: 0.167em; height: 0px;\"></span><span id=\"MJXc-Node-3286\" class=\"mjx-msubsup\"><span class=\"mjx-base\"><span id=\"MJXc-Node-3287\" class=\"mjx-texatom\"><span id=\"MJXc-Node-3288\" class=\"mjx-mrow\"><span id=\"MJXc-Node-3289\" class=\"mjx-mrow\"><span id=\"MJXc-Node-3290\" class=\"mjx-mo\"><span class=\"mjx-char MJXc-TeX-main-R\" style=\"padding-top: 0.438em; padding-bottom: 0.601em;\">{</span></span><span id=\"MJXc-Node-3291\" class=\"mjx-mi\"><span class=\"mjx-char MJXc-TeX-math-I\" style=\"padding-top: 0.492em; padding-bottom: 0.492em; padding-right: 0.06em;\">f</span></span><span id=\"MJXc-Node-3292\" class=\"mjx-mrow MJXc-space1\"><span id=\"MJXc-Node-3293\" class=\"mjx-mo\"><span class=\"mjx-char MJXc-TeX-main-R\" style=\"padding-top: 0.438em; padding-bottom: 0.601em;\">(</span></span><span id=\"MJXc-Node-3294\" class=\"mjx-mi\"><span class=\"mjx-char MJXc-TeX-math-I\" style=\"padding-top: 0.22em; padding-bottom: 0.274em;\">n</span></span><span id=\"MJXc-Node-3295\" class=\"mjx-mo\"><span class=\"mjx-char MJXc-TeX-main-R\" style=\"padding-top: 0.438em; padding-bottom: 0.601em;\">)</span></span></span><span id=\"MJXc-Node-3296\" class=\"mjx-mo\"><span class=\"mjx-char MJXc-TeX-main-R\" style=\"padding-top: 0.438em; padding-bottom: 0.601em;\">}</span></span></span></span></span></span><span class=\"mjx-sup\" style=\"font-size: 70.7%; vertical-align: 0.71em; padding-left: 0px; padding-right: 0.071em;\"><span id=\"MJXc-Node-3297\" class=\"mjx-texatom\" style=\"\"><span id=\"MJXc-Node-3298\" class=\"mjx-mrow\"><span id=\"MJXc-Node-3299\" class=\"mjx-mn\"><span class=\"mjx-char MJXc-TeX-main-R\" style=\"padding-top: 0.383em; padding-bottom: 0.329em;\">2</span></span></span></span></span></span></span></span></span></span></span></span></span></span><span class=\"mjx-strut\"></span></span></span></span></span></span><span id=\"MJXc-Node-3301\" class=\"mjx-mspace\" style=\"width: 0.167em; height: 0px;\"></span></span></span><span class=\"MJX_Assistive_MathML\" role=\"presentation\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\"><munderover><mo movablelimits=\"false\">∑</mo><mrow class=\"MJX-TeXAtom-ORD\"><mi>n</mi><mo>=</mo><mn>1</mn></mrow><mrow class=\"MJX-TeXAtom-ORD\"><mn>10</mn></mrow></munderover><mtable rowspacing=\"4pt\" columnspacing=\"1em\"><mtr><mtd><mn>1</mn></mtd></mtr><mtr><mtd><mrow class=\"MJX-TeXAtom-ORD\"><mrow class=\"MJX-TeXAtom-ORD\"><mover><mrow><mspace width=\"thinmathspace\"></mspace><msup><mrow class=\"MJX-TeXAtom-ORD\"><mrow><mo>{</mo><mi>f</mi><mrow><mo>(</mo><mi>n</mi><mo>)</mo></mrow><mo>}</mo></mrow></mrow><mrow class=\"MJX-TeXAtom-ORD\"><mn>2</mn></mrow></msup></mrow><mo stretchy=\"false\">¯</mo></mover></mrow></mrow></mtd></mtr></mtable><mspace width=\"thinmathspace\"></mspace></math></span></span>";

        String test3html = "\\[\n" + "\\langle \\mu_{m} \\rangle = \n" + "\\frac{{\\displaystyle \\sum_{-J}^{J}}{-g\\mu_{\\beta}M_{J}B.\\exp{\\left(\\frac{-g\\mu_{\\beta}M_{J}B}{kT}\\right)}}}\n" + "{{\\displaystyle \\sum_{-J}^{J}}{\\exp{\\left(\\frac{-g\\mu_{\\beta}M_{J}B}{kT}\\right)}}}\n" + "\\]";

        String test4 = "\\documentclass[12pt]{article}\n" + "\\usepackage{amsmath}\n" + "\\begin{document}\n" + "\\begin{align}\n" + "<\\mu_{m}> = \\frac{\\sum_{-J}^{J}{-g\\mu_{\\beta}M_{J}B.\\exp{\\left(\\frac{-g\\mu_{\\beta}M_{J}B}{kT}\\right)}}}{\\sum_{-J}^{J}{\\exp{\\left(\\frac{-g\\mu_{\\beta}M_{J}B}{kT}\\right)}}}\n" + "\\end{align}\n" + "\\end{document}";

        String test5 = "<div class=\"contain-exam-body\">\n" + "\t\t\t\t<div class=\"box-exam\" style=\"height: 677.312px;\">\n" + "\t\t\t\t\t<div style=\"clear:both;\">\n" + "\t\t\t\t\t\t<div class=\"popcontents_itempool\" id=\"divItemPool\"><dl id=\"15553843\" data-index=\"0\" class=\"qubox half\" style=\"width: 100%;\"><dt><p></p></dt><dd style=\"line-height:130%; font-family: 돋움; font-size: 14px;\" class=\"itemlist\" name=\"divLML\" itemid=\"15553843\" groupid=\"NaN\" point=\"0\" hassound=\"false\" soundurl=\"\" itemcode=\"\" applyyear=\"2020\" pcode=\"\"><div style=\"line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divQuestion\"><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">다음은 어떤 고체의 에너지띠 구조에 대한 설명이다 </span></div><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><div><table style=\"line-height:130%; font-family: 돋움; font-size: 14px;border-spacing:0px;border-collapse:collapse;width:100%;\" name=\"divTable\"><tbody><tr><td style=\"padding:4px;border-style:solid;border-color:Black;display: inline-block;border-width:1px 1px 1px 1px ;line-height:130%; font-family: 돋움; font-size: 14px;width:100%;\" name=\"divTableCell\"><div style=\"text-align: center; line-height: 130%; font-family: 돋움; font-size: 14px; position: relative;\" name=\"divParagraph\"><img style=\"width: 136px; height: 180px; line-height: 130%; font-family: 돋움; font-size: 14px; cursor: pointer;\" src=\"https://ai.ebs.co.kr/ebs/imageView/xip/ImageView?imgurl=resource/book/2763/15553843_000.png\"><span class=\"magnifier\" onclick=\"imageClickEvent($(this).siblings('img'));\"><em>확대보기</em><svg class=\"font-icons\"><use xlink:href=\"#svg-round-plus\"></use></svg></span></div><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">  그림은 ⓐ </span><span style=\"white-space:pre-wrap;font-size:10px;text-decoration:underline;line-height:130%; font-family: 돋움; font-size: 14px;\">전자가 채워진 띠와 비어 있는 띠가 겹쳐진 고체</span><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">의 에너지띠 구조 를 나타낸 것이다. A 부분은 전자가 채워진 것을, B 부분은 전자가 비어 있는 것을 나타낸 것이다. </span></div></td></tr></tbody></table></div></div><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">이에 대한 설명으로 옳은 것만을 &lt;보기&gt;에서 있는 대로 고른 것은? </span></div><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><div><table style=\"line-height:130%; font-family: 돋움; font-size: 14px;border-spacing:0px;border-collapse:collapse;width:100%;\" name=\"divTable\"><tbody><tr><td style=\"padding:4px;border-style:solid;border-color:Black;border-width:0px 0px 1px 0px ;line-height:130%; font-family: 돋움; font-size: 14px;width:40.74074074074074%;\" name=\"divTableCell\" colspan=\"2\"><div style=\"width:2px;height:18px;\" name=\"divParagraph\"></div></td><td style=\"padding:4px;line-height:130%; font-family: 돋움; font-size: 14px;width:18.51851851851852%;\" name=\"divTableCell\" rowspan=\"2\"><div style=\"text-align:Center;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">&lt; 보 기 &gt;</span></div></td><td style=\"padding:4px;border-style:solid;border-color:Black;border-width:0px 0px 1px 0px ;line-height:130%; font-family: 돋움; font-size: 14px;width:40.74074074074074%;\" name=\"divTableCell\" colspan=\"2\"><div style=\"width:2px;height:18px;\" name=\"divParagraph\"></div></td></tr><tr><td style=\"padding:4px;border-style:solid;border-color:Black;border-width:1px 0px 0px 1px ;line-height:130%; font-family: 돋움; font-size: 14px;width:40.74074074074074%;\" name=\"divTableCell\" colspan=\"2\"><div style=\"width:2px;height:18px;\" name=\"divParagraph\"></div></td><td style=\"padding:4px;border-style:solid;border-color:Black;border-width:1px 1px 0px 0px ;line-height:130%; font-family: 돋움; font-size: 14px;width:57.407407407407405%;\" name=\"divTableCell\" colspan=\"2\"><div style=\"width:2px;height:18px;\" name=\"divParagraph\"></div></td></tr><tr><td style=\"padding:4px;border-style:solid;border-color:Black;border-width:0px 0px 0px 1px ;line-height:130%; font-family: 돋움; font-size: 14px;width:1.8518518518518516%;\" name=\"divTableCell\"><div style=\"width:2px;height:18px;\" name=\"divParagraph\"></div></td><td style=\"padding:4px;line-height:130%; font-family: 돋움; font-size: 14px;width:96.29629629629629%;\" name=\"divTableCell\" colspan=\"3\"><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">ㄱ. 반도체는 ⓐ보다 전기 전도성이 좋다.</span></div><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">ㄴ. A에 있는 전자들의 에너지 준위는 모두 같다.</span></div><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">ㄷ. 전자가 A에서 B로 전이할 때 에너지를 흡수한다.</span></div></td><td style=\"padding:4px;border-style:solid;border-color:Black;border-width:0px 1px 0px 0px ;line-height:130%; font-family: 돋움; font-size: 14px;width:1.8518518518518516%;\" name=\"divTableCell\"><div style=\"width:2px;height:18px;\" name=\"divParagraph\"></div></td></tr><tr><td style=\"padding:4px;border-style:solid;border-color:Black;border-width:0px 1px 1px 1px ;line-height:130%; font-family: 돋움; font-size: 14px;width:100%;\" name=\"divTableCell\" colspan=\"5\"><div style=\"width:2px;height:18px;\" name=\"divParagraph\"></div></td></tr></tbody></table></div></div><ul><div style=\"text-align:right;font-size:12px;margin-bottom:12px;\"></div><li class=\"dontsplit odd\" iscorrectanswer=\"False\"><div style=\"width:20px; display:table-cell;\" class=\"numbering\">①</div><div style=\"display:table-cell;min-height:20px;padding-left:20px;height:22px;\" id=\"ddd\"><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">ㄴ </span></div></div></li><li class=\"dontsplit\" iscorrectanswer=\"False\"><div style=\"width:20px; display:table-cell;\" class=\"numbering\">②</div><div style=\"display:table-cell;min-height:20px;padding-left:20px;height:22px;\" id=\"ddd\"><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">ㄷ </span></div></div></li><li class=\"dontsplit odd\" iscorrectanswer=\"False\"><div style=\"width:20px; display:table-cell;\" class=\"numbering\">③</div><div style=\"display:table-cell;min-height:20px;padding-left:20px;height:22px;\" id=\"ddd\"><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">ㄱ, ㄴ </span></div></div></li><li class=\"dontsplit\" iscorrectanswer=\"False\"><div style=\"width:20px; display:table-cell;\" class=\"numbering\">④</div><div style=\"display:table-cell;min-height:20px;padding-left:20px;height:22px;\" id=\"ddd\"><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">ㄱ, ㄷ </span></div></div></li><li class=\"dontsplit odd\" iscorrectanswer=\"False\"><div style=\"width:20px; display:table-cell;\" class=\"numbering\">⑤</div><div style=\"display:table-cell;min-height:20px;padding-left:20px;height:22px;\" id=\"ddd\"><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">ㄴ, ㄷ </span></div></div></li></ul><div><fieldset name=\"divExplanation\" class=\"explain\" id=\"Explanation\" style=\"display:none;\"><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-weight:Bold;font-size:10px;line-height:130%; font-family: 돋움; font-size: 14px;\">고체의 에너지띠</span></div><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">전자가 채워진 A는 원자가 띠이고, 전자가 비어 있는 B는 전도 띠이다. A와 B가 겹쳐진 도체는 원자가 띠와 전도띠가 띠 간격이 없이 서로 붙어 있어, 원자가 띠의 전자들이 약간의 에너지를 얻으면 쉽게 전도띠로 전이하여 자유롭게 이동할 수 있다. 이러한 자유 전자에 의해 전류가 쉽게 흐를 수 있다.</span></div><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">ㄱ. 도체인 ⓐ는 반도체보다 전기 전도성이 좋다.</span></div><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">ㄴ. 고체에서 전자의 에너지 준위는 미세하게 겹쳐 있어 거의 연속적으로 분포하는 에너지띠를 이루므로 A에 있는 전자의 에너지 준위는 모두 같지 않다.</span></div><div style=\"text-align:Justify;line-height:130%; font-family: 돋움; font-size: 14px;\" name=\"divParagraph\"><span style=\"white-space:pre-wrap;font-size:11px;line-height:130%; font-family: 돋움; font-size: 14px;\">ㄷ. 에너지 준위가 낮은 원자가 띠 A에서 에너지 준위가 높은 전도띠 B로 전자가 전이할 때에는 에너지를 흡수한다.</span></div></fieldset></div></div></dd></dl></div>\n" + "\t\t\t\t\t\t<div>\n" + "\t\t\t\t\t\t\t\n" + "\t\t\t\t\t\t</div>\n" + "\t\t\t\t\t</div>\n" + "\t\t\t\t\t<div id=\"MessageWindow\" style=\"width: 100%; height: 100%; left: 0px; top: 0px; background-color: white; text-align: center; visibility: initial; display: none;\"><div class=\"modal_wrap small modalLoading_wrap active\">    <section class=\"modal loading modal_notify\">        <div class=\"modal_container\">            <div class=\"modal_loading_content\">                <div class=\"lds-roller\">                    <div></div>                    <div></div>                    <div></div>                    <div></div>                    <div></div>                    <div></div>                    <div></div>                    <div></div>                </div>            </div>        </div>    </section></div></div>\n" + "\t\t\t\t</div>\n" + "\t\t\t\t<div class=\"box-skip\">\n" + "\t\t\t\t\t<button type=\"button\" class=\"btn-skip-exam\">문제 건너뛰기</button>\n" + "\t\t\t\t</div>\n" + "\t\t\t</div>";
        String outputText = Jsoup.clean(htmlString, new Whitelist());
        System.out.println(outputText);

        //outputText 인코딩 확인하기


        String originalStr = outputText;
        byte[] bytes = originalStr.getBytes(StandardCharsets.UTF_8);
        originalStr = new String(bytes);

        String[] charSet = {"utf-8", "euc-kr", "ksc5601", "iso-8859-1", "x-windows-949"};
        for (String s : charSet) {
            for (String value : charSet) {
                try {
                    System.out.println("[" + s + "," + value + "]" + new String(originalStr.getBytes(s), value));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }


        //2.save png at local
        PngMathRendererOptions options = new PngMathRendererOptions();

        options.setResolution(150);


        // 배율을 300%로 지정합니다.
        options.setScale(3000);

        System.out.println("show preamble : " + options.getPreamble());


        // 전경색을 지정합니다.
        options.setTextColor(Color.BLACK);

        // 배경색을 지정합니다.
        options.setBackgroundColor(Color.WHITE);

        // 로그 파일의 출력 스트림을 지정합니다.
        options.setLogStream(new ByteArrayOutputStream());

        // 콘솔에 터미널 출력을 표시할지 여부를 지정합니다.
        options.showTerminal(true);

        // 결과 이미지의 치수가 기록될 변수입니다.
        com.aspose.tex.Size2D size = new com.aspose.tex.Size2D.Float();

        // 수식 이미지에 대한 출력 스트림을 만듭니다.
        final OutputStream stream = new FileOutputStream("D:\\dev\\newDoctorMath\\simple-formula14.png");

        // PNG로 렌더링
        PngMathRenderer mathRenderer = new PngMathRenderer();

        mathRenderer.render(outputText, stream, options, size);

        // 다른 결과를 표시합니다.
        System.out.println(options.getErrorReport());
        System.out.println();
        System.out.println("Size: " + size.getWidth() + "x" + size.getHeight());
    }

    public void testLatextoPng2() throws Exception {
        TeXOptions options = TeXOptions.consoleAppOptions(TeXConfig.objectLaTeX());
        // Specify the output working directory for the output file
        options.setOutputWorkingDirectory(new OutputFileSystemDirectory("D:\\dev\\newDoctorMath\\"));

        // Initialize the TeXOptions and PngSaveOptions for saving LaTeX to PNG image format
        PngSaveOptions pngSaveOptions = new PngSaveOptions();
        pngSaveOptions.setResolution(300);

        options.setSaveOptions(pngSaveOptions);

        // Perform the LaTeX to PNG image rendering using TexJob class
        new TeXJob("SavedLatex.png", new ImageDevice(), options).run();
    }

    public void useJlatexToConvert() throws Exception {
        String htmlString = "" + "<div><h1>예제입니다.</h1><span>Convert HTML to Text</span>" + "<div><span>(2)<span> \\( \\frac{5}{16} \\quad a=2 b-1 \\)" + "                \\( y_{62}^{\\frac{3}{8}} \\)" + "                (4) \\( \\frac{7}{16} \\)" + "                (3) \\( \\frac{15}{32}=\\frac{2}{32} \\)" + "<div></div>";

        String outputText = Jsoup.clean(htmlString, new Whitelist());
        System.out.println(outputText);


    }
}
