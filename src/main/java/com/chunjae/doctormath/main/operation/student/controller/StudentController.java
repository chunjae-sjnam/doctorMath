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
    public String list(StudentReqDto studentReqDto, Model model) throws Exception{

        String condition = studentReqDto.getCondition();    //검색조건
        String keyword = studentReqDto.getKeyword();        //검색어

        if(condition != null && condition.equals("name")){  //이름
            studentReqDto.setName(keyword);
        }

        if(condition != null && condition.equals("grade")){ //학년

            String curri = keyword.substring(0, 1);           //학교급
            String grade = keyword.substring(1);    //학년

            if(curri.equals("초")){
                studentReqDto.setCurri("E");

            } else if(curri.equals("중")){
                studentReqDto.setCurri("M");

            } else if(curri.equals("고")){
                studentReqDto.setCurri("H");
            }
            studentReqDto.setGrade(grade);
        }

        if(condition != null && condition.equals("status")){    //상태

            String status = keyword;

            if(status.equals("재원")){
                studentReqDto.setStatus("S");

            } else if(status.equals("휴원")){
                studentReqDto.setStatus("P");

            } else if(status.equals("퇴원")){
                studentReqDto.setStatus("O");
            }
        }

        if(condition != null && condition.equals("phone")) {    //연락처
            studentReqDto.setShtell(keyword);
        }

        List<StudentResDto> list = studentService.getList(studentReqDto);
//        log.info("list ==>{}", list);

        model.addAttribute("list", list);
        return "main/operation/student";
    }

    /**
     * 시도 리스트
     */
    @RequestMapping("/sidoList")
    public ArrayList<String> sidoList(Model model) throws Exception {

        ArrayList<String> sidoList = new ArrayList<>();

        List<StudentResDto> list = studentService.getSido();

        for (var i=0; i<list.size(); i++){
            String sido = list.get(i).getSido();
            sidoList.add(sido);
        }
        log.info("sido>>>>>>" + sidoList);
        return sidoList;
    }

    /**
     * 학생 등록
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public void insert(StudentReqDto studentReqDto) throws Exception{

        String seq = studentService.selectSeq();

        if(seq != null){
            studentService.updateSeq();

        } else {
            studentService.insertSeq();
        }

        seq = studentService.selectSeq();
        log.info("seq>>>>>>>>" + seq);
        String str = "0000000" + seq;
        log.info("str>>>>>>>>" + str);

        String studentCode = "A" + str.substring(str.length()-7);
        log.info("studentCode>>>>>>>>" + studentCode);

        studentReqDto.setStudentCode(studentCode);
        log.info("studentReqDto>>>>>>>>" + studentReqDto);

        //학생 등록
        studentService.register(studentReqDto);

        //학생 아이디 발급 추가
        String code = studentService.selectCode(studentReqDto);
        log.info("code>>>>>>>>" + code);

        if(code == null){
            log.info("!!!!!!!!!!!!!!!!!!!!!!!!");
            String studentID = studentService.selectID();
            log.info("studentID>>>>>>>>" + studentID);
        }
    }

    /**
     * 학생 상세 정보
     */
    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    @ResponseBody
    public StudentResDto detail(StudentReqDto studentReqDto) throws Exception{
        return studentService.getDetail(studentReqDto);
    }

    /**
     * 학생 상세 정보 수정
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(@RequestParam Map<String, Object> param, Model model, HttpServletResponse response) throws Exception{

        Map<String, Object> resultMap = new HashMap<>();

        int updateStat = studentService.update(param);
        if(updateStat == 1){
            resultMap.put("result", true);

        } else {
            resultMap.put("result", false);
        }
        System.out.println("resultMap>>>>>>" + resultMap);
        return resultMap;
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
            log.error(e.getMessage());
            result.put("msg", "등록 실패");
        }
        return result;
    }

}
