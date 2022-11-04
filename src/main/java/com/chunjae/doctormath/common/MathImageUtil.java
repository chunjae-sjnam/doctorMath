package com.chunjae.doctormath.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class MathImageUtil {
    protected static Logger logger = LoggerFactory.getLogger(MathImageUtil.class);

    /**
     * 수식 이미지 변환
     * @param fileName png파일명
     * @param mathContent html형식의 수식 데이터
     * @return Map:imagePath, imageFileName
     * @throws Exception
     */
    public static Map<String, String> renderHtmlToPng(String fileName, String mathContent)throws  Exception{
        //temp jps and png file location
        String filePath = "D:\\dev\\doctorMath\\src\\main\\webapp\\views";
        String jspFilePath = filePath + File.separator + fileName + ".jsp";
        File file = new File(jspFilePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>");
        writer.write("<head>");
        writer.write("\r\n\t <script src='https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.2/MathJax.js?config=TeX-MML-AM_HTMLorMML'></script>");
        writer.write("\r\n\t <script type=\"text/x-mathjax-config\">\n" +
                "\tMathJax.Hub.Config({\n" +
                "\t\t \"HTML-CSS\": {\n" +
                // "\t\t\t scale:200,\n"+
                "\t\t\t styles:{\n" +
                "\t\t\t \".MathJax .merror\": {\n" +
                "\t\t\t \"font-style\": \"normal\",\n" +
                "\t\t\t \"font-size\":  \"30px\"\n" +
                "\t\t\t }},\n" +
                "\t\t\t minScaleAdjust: 200,\n" +
                "\t\t\t linebreaks: { automatic: false }\n" +
                "\t\t },\n" +
                "\t\t \"CommonHTML\": { minScaleAdjust: 200, linebreaks: { automatic: false } }\n" +
                "\t });\n" +
                "</script>");

        writer.write("\r\n\t </head>");
        writer.write("<style>\n" +
                "    @import url( \"https://fonts.googleapis.com/earlyaccess/nanummyeongjo.css\" );\n" +
                "    body {\n" +
                "        font-family: 'Nanum Myeongjo', serif;\n" +
                "        font-size: 20px;\n" +
                "    }\n" +
                "</style>");

        writer.write("\r\n\t <body>");
        writer.write(mathContent);
        writer.write("\r\n\t </body>");
        writer.close();


        String pngPath = filePath + File.separator + fileName + ".png";
        String host = "http://localhost:8080/views/";
        String cmd = "C:\\Program Files\\wkhtmltopdf\\bin\\wkhtmltoimage.exe --no-stop-slow-scripts --enable-javascript --javascript-delay 10000 "+ host+fileName+".jsp"+ " "+pngPath;
        logger.info("cmd :: " + cmd);

        Process process = Runtime.getRuntime().exec(cmd);
        process.waitFor();

        Map<String, String> imageInfo =new HashMap<>();
        imageInfo.put("imagePath", pngPath);
        imageInfo.put("imageFileName", fileName + ".png");

        return imageInfo;
    }
}
