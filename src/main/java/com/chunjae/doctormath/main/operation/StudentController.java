package com.chunjae.doctormath.main.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/operation")
public class StudentController {

    protected Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    //학생 리스트
    @RequestMapping("/list")
    public String list(@RequestParam Map<String,Object> param, Model model) throws Exception{

        Map<String,Object> resultMap = new HashMap<>();

        String HakwonCode = "H0000194";
        String TeacherCode = "H0000194";
        String Name = null;
        String Curri = null;
        String Grade = null;
        String Status = null;
        String SHtell = null;
        String ClassCode = null;

        param.put("HakwonCode", HakwonCode);
        param.put("TeacherCode", TeacherCode);
        param.put("Name", Name);
        param.put("Curri", Curri);
        param.put("Grade", Grade);
        param.put("Status", Status);
        param.put("SHtell", SHtell);
        param.put("ClassCode", ClassCode);

        resultMap = studentService.getList(param);

        model.addAttribute("resultMap", resultMap);
        return "main/operation/student";
    }

    //학생 상세 정보
    @RequestMapping(value = "/detailList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> detailList(@RequestParam Map<String, Object> param) throws Exception {

        String StudentCode = String.valueOf(param.getOrDefault("StudentCode",""));

        Map<String,Object> resultMap = new HashMap<>();
        resultMap = studentService.getDetailList(param);
        resultMap.put("result", true);

        return resultMap;
    }

    //학생 상세 정보 수정
    @RequestMapping(value = "/updateList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateList(@RequestParam Map<String, Object> param, Model model, HttpServletResponse response) throws Exception{

        Map<String, Object> resultMap = new HashMap<>();
        System.out.println("param>>>>>" + param);

        int updateStat = studentService.updateList(param);
        if(updateStat == 1){
            resultMap.put("result", true);

        } else {
            resultMap.put("result", false);
        }
        System.out.println("resultMap>>>>>>" + resultMap);
        return resultMap;
    }

    //파일 다운로드
    @RequestMapping(value = "/getDownload")
    public void excelDownload(HttpServletResponse response, HttpServletRequest request, @RequestParam Map<String, String> paramMap) {

        String fileName = paramMap.get("fileName");
        String path = request.getServletContext().getRealPath("/files/" + fileName);

        File file = new File(path);
        FileInputStream fileInputStream = null;
        ServletOutputStream servletOutputStream = null;

        try{
            String downName = null;
            String browser = request.getHeader("User-Agent");

            //파일 인코딩
            if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){
                downName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");

            }else{
                downName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }

            response.setHeader("Content-Disposition","attachment;filename=\"" + downName+"\"");
            response.setContentType("application/octer-stream");
            response.setHeader("Content-Transfer-Encoding", "binary;");

            fileInputStream = new FileInputStream(file);
            servletOutputStream = response.getOutputStream();

            byte b [] = new byte[1024];
            int data = 0;

            while((data=(fileInputStream.read(b, 0, b.length))) != -1){
                servletOutputStream.write(b, 0, data);
            }
            servletOutputStream.flush();    //출력

        }catch (Exception e) {
            e.printStackTrace();

        }finally{
            if(servletOutputStream!=null){
                try{
                    servletOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(fileInputStream!=null){
                try{
                    fileInputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    //파일 업로드
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> fileUpload(MultipartHttpServletRequest request) throws Exception {

        Map<String, Object> result = new HashMap<>();
        MultipartFile excelFile = request.getFile("excel_file");
        File destFile = new File("D:\\" + excelFile.getOriginalFilename());

        try {
            if(!excelFile.isEmpty()) {
                
                excelFile.transferTo(destFile);
                studentService.fileUpload(destFile);    //엑셀 업로드
                destFile.delete();
                
                result.put("code", "SUCCESS");
                result.put("msg", "등록 성공");

            } else {
                result.put("code", "FAIL");
                result.put("msg", "등록 실패");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.put("msg", "등록 실패");
        }
        return result;
    }

}
