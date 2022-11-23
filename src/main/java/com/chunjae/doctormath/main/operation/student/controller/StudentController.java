package com.chunjae.doctormath.main.operation.student.controller;

import com.chunjae.doctormath.main.operation.student.dto.request.StudentReqDto;
import com.chunjae.doctormath.main.operation.student.dto.response.StudentResDto;
import com.chunjae.doctormath.main.operation.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("operation")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;

    /**
     * 학생 리스트 가져오기
     */
    @RequestMapping("/list")
    public String studentList(StudentReqDto studentReqDto, Model model) throws Exception{

        List<StudentResDto> list = studentService.studentList(studentReqDto);

        model.addAttribute("list", list);
        return "main/operation/student";
    }

    /**
     * 시도 리스트
     */
    @RequestMapping("/sidoList")
        public String sidoList(Model model) throws Exception{

        ArrayList<String> sidoList = studentService.sidoList();

        model.addAttribute("sidoList", sidoList);
        log.info("sidoList>>>>>>>" + sidoList);
        return "main/operation/student";
    }

    /**
     * 학생 등록
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public int insertStudent(StudentReqDto studentReqDto) throws Exception{

        String studentCode = studentService.selectStudentSeq();
        studentReqDto.setStudentCode(studentCode);

        String parentCode = studentService.selectParentSeq();
        studentReqDto.setParentCode(parentCode);

        int a = 0;
        int b = 0;
        int c = 0;

        try {
            //MEM_Student
            a = studentService.insertMemStudent(studentReqDto);

            //MEM_Member
            b = studentService.insertMemMember(studentReqDto);

            //t_mem_parents
            c = studentService.insertMemParents(studentReqDto);

        } catch (Exception e) {
            log.error("insertStudent Error : " + e.getMessage());
            return -1;
        }
        return a+b+c;
    }

    /**
     * 학생 상세 정보
     */
    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    @ResponseBody
    public StudentResDto detailStudent(StudentReqDto studentReqDto) throws Exception{
        return studentService.detailStudent(studentReqDto);
    }

    /**
     * 학생 상세 정보 수정
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public int updateStudent(StudentReqDto studentReqDto) throws Exception{
        return studentService.updateStudent(studentReqDto);
    }

    /**
     * 파일 다운로드
     */
    @RequestMapping(value = "/getDownload")
    public void excelDownload(HttpServletResponse response, HttpServletRequest request, @RequestParam Map<String, String> paramMap) throws Exception{

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

    /**
     * 파일 업로드
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> excelUpload(MultipartHttpServletRequest request) throws Exception {

        Map<String, Object> result = new HashMap<>();
        MultipartFile excelFile = request.getFile("excel_file");
        File destFile = new File("D:\\" + excelFile.getOriginalFilename());

        try {
            if(!excelFile.isEmpty()) {
                
                excelFile.transferTo(destFile);
                studentService.excelUpload(destFile);    //엑셀 업로드
                destFile.delete();
                
                result.put("code", "SUCCESS");
                result.put("msg", "등록 성공");

            } else {
                result.put("code", "FAIL");
                result.put("msg", "등록 실패");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            result.put("msg", "등록 실패");
        }
        return result;
    }
}
