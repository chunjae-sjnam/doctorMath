package com.chunjae.doctormath.main.operation.student.service;

import com.chunjae.doctormath.main.operation.student.dto.request.FileReqDto;
import com.chunjae.doctormath.main.operation.student.dto.request.SeqReqDto;
import com.chunjae.doctormath.main.operation.student.dto.request.StudentReqDto;
import com.chunjae.doctormath.main.operation.student.dto.response.StudentResDto;
import com.chunjae.doctormath.main.operation.student.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class StudentService {

    private final StudentMapper studentMapper;

    public List<StudentResDto> studentList(StudentReqDto studentReqDto) {

        String condition = studentReqDto.getCondition();    //검색조건
        String keyword = studentReqDto.getKeyword();        //검색어

        //이름
        if(condition != null && condition.equals("name")){
            studentReqDto.setName(keyword);
        }

        //학년
        if(condition != null && condition.equals("grade")){

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

        //상태
        if(condition != null && condition.equals("status")){

            String status = keyword;

            if(status.equals("재원")){
                studentReqDto.setStatus("S");

            } else if(status.equals("휴원")){
                studentReqDto.setStatus("P");

            } else if(status.equals("퇴원")){
                studentReqDto.setStatus("O");
            }
        }

        //연락처
        if(condition != null && condition.equals("phone")) {
            studentReqDto.setShtell(keyword);
        }

        //temp
        studentReqDto.setHakwonCode("H0000031");
        studentReqDto.setTeacherCode("H0000031");

        return studentMapper.studentList(studentReqDto);
    }

    public StudentResDto detailStudent(StudentReqDto studentReqDto) throws Exception {
        return studentMapper.detailStudent(studentReqDto);
    }

    public ArrayList<String> sidoList() throws Exception {
        return studentMapper.sidoList();
    }

    public String userKey(String key) throws Exception {
        return studentMapper.selectSeq(new SeqReqDto(key));
    }

    public String selectStudentSeq() throws Exception{

        String seq = userKey("S");

        if(seq != null){
            studentMapper.updateSeq(new SeqReqDto("S"));

        } else {
            studentMapper.insertSeq(new SeqReqDto("S"));
        }

        seq = userKey("S");
        String str = "0000000" + seq;

        String studentCode = "S" + str.substring(str.length()-7);
        log.info("studentCode>>>>>>>>" + studentCode);

        return studentCode;
    }

    public String selectParentSeq() throws Exception{

        String seq = userKey("P");

        if(seq != null){
            studentMapper.updateSeq(new SeqReqDto("P"));

        } else {
            studentMapper.insertSeq(new SeqReqDto("P"));
        }

        seq = userKey("P");
        String str = "0000000" + seq;

        String parentCode = "P" + str.substring(str.length()-7);
        log.info("parentCode>>>>>>>>" + parentCode);

        return parentCode;
    }

    public int insertMemStudent(StudentReqDto studentReqDto) throws Exception {

        //temp
        studentReqDto.setHakwonCode("H0000031");
        studentReqDto.setTeacherCode("H0000031");

        return studentMapper.insertMemStudent(studentReqDto);
    }

    public int insertMemMember(StudentReqDto studentReqDto) throws Exception {

        String shtell = studentReqDto.getShtell();
        String userID;
        String parentID;

        //학생, 학부모 아이디 발급 추가
        if(shtell == null || shtell.isEmpty()){ //임의 생성

            String numStr = "";

            for(int i=0; i<8; i++) {
                //0~9 까지 난수 생성
                String num = Integer.toString(new Random().nextInt(10));
                numStr += num;
            }
            userID = "S" + numStr;
            parentID = "P" + numStr;

        } else {    //학생 연락처 8자리 생성
            userID = "S" + shtell.substring(3);
            parentID = "P" + shtell.substring(3);
        }

        studentReqDto.setUserID(userID);
        studentReqDto.setParentID(parentID);

        int studentCnt = studentMapper.insertMemMemberStudent(studentReqDto);   //학생 등록
        int parentCnt = studentMapper.insertMemMemberParent(studentReqDto);     //학부모 등록
        int cnt = studentCnt+ parentCnt;

        return cnt;
    }

    public int insertMemParents(StudentReqDto studentReqDto) throws Exception{
        return studentMapper.insertMemParents(studentReqDto);
    }

    public int updateStudent(StudentReqDto studentReqDto) throws Exception{
        return studentMapper.updateStudent(studentReqDto);
    }

    public void excelUpload(File destFile) throws Exception {

        ArrayList<ArrayList<String>> listResult = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(destFile.getAbsolutePath());

            if (destFile.getAbsolutePath().endsWith("xls")) {    //xls 파일
                HSSFWorkbook workbook = new HSSFWorkbook(fis);

                HSSFSheet sheet = workbook.getSheetAt(0);
                int rows = sheet.getPhysicalNumberOfRows();

                for (int rowindex = 3; rowindex < rows; rowindex++) {   //행을 읽는다

                    HSSFRow row = sheet.getRow(rowindex);
                    ArrayList<String> list = new ArrayList<>();

                    if (row != null) {
                        int cells = row.getPhysicalNumberOfCells(); //셀의 수

                        for (int columnindex = 1; columnindex < cells; columnindex++) { //셀값을 읽는다

                            HSSFCell cell = row.getCell(columnindex);
                            String value = "";

                            if (cell == null || cell.getCellType() == CellType.BLANK) { //셀이 빈값일경우를 위한 널체크
                                value = "";

                            } else {
                                //타입별로 내용 읽기
                                switch (cell.getCellType()) {
                                    case FORMULA:
                                        value = cell.getCellFormula();
                                        break;
                                    case NUMERIC:
                                        value = cell.getNumericCellValue() + "";
                                        break;
                                    case STRING:
                                        value = cell.getStringCellValue() + "";
                                        break;
                                    case ERROR:
                                        value = cell.getErrorCellValue() + "";
                                        break;
                                }
                            }
                            System.out.println(rowindex + "번 행 : " + columnindex + "번 열 값은: " + value);
                            list.add(value);    //읽은 셀들을 list(행)에 추가
                        }
                    }
                    listResult.add(list);    //list(행)을 listResult(열)에 추가
                }

            } else if (destFile.getAbsolutePath().endsWith("xlsx")) {   //xlsx 파일
                XSSFWorkbook workbook = new XSSFWorkbook(fis);

                XSSFSheet sheet = workbook.getSheetAt(0);
                int rows = sheet.getPhysicalNumberOfRows();

                for (int rowindex = 3; rowindex < rows; rowindex++) {   //행을 읽는다

                    XSSFRow row = sheet.getRow(rowindex);
                    ArrayList<String> list = new ArrayList<>();

                    if (row != null) {
                        int cells = row.getPhysicalNumberOfCells(); //셀의 수

                        for (int columnindex = 1; columnindex < cells; columnindex++) { //셀값을 읽는다

                            XSSFCell cell = row.getCell(columnindex);
                            String value = "";

                            if (cell == null || cell.getCellType() == CellType.BLANK) { //셀이 빈값일경우를 위한 널체크
                                value = "";

                            } else {
                                //타입별로 내용 읽기
                                switch (cell.getCellType()) {
                                    case FORMULA:
                                        value = cell.getCellFormula();
                                        break;
                                    case NUMERIC:
                                        value = cell.getNumericCellValue() + "";
                                        break;
                                    case STRING:
                                        value = cell.getStringCellValue() + "";
                                        break;
                                    case ERROR:
                                        value = cell.getErrorCellValue() + "";
                                        break;
                                }
                            }
                            System.out.println(rowindex + "번 행 : " + columnindex + "번 열 값은: " + value);
                            list.add(value);    //읽은 셀들을 list(행)에 추가
                        }
                    }
                    listResult.add(list);    //list(행)을 listResult(열)에 추가
                }
            }
            fis.close();

            for (int i = 0; i < listResult.size(); i++) {
                Map<String, Object> listMap = new HashMap<>();

                FileReqDto fileReqDto = new FileReqDto();

                fileReqDto.setName(listResult.get(i).get(0));
                fileReqDto.setCurri(listResult.get(i).get(1));
                fileReqDto.setGrade(listResult.get(i).get(2));
                fileReqDto.setUserID(listResult.get(i).get(3));
                fileReqDto.setParentID(listResult.get(i).get(4));
                fileReqDto.setStatus(listResult.get(i).get(5));
                fileReqDto.setShtell(listResult.get(i).get(6));
                fileReqDto.setPhtell(listResult.get(i).get(7));

                String schoolName = listResult.get(i).get(8);
                String[] str = schoolName.split(" ");

                fileReqDto.setSido(str[0]);
                fileReqDto.setGu(str[1]);
                fileReqDto.setSchool(str[2]);

                fileReqDto.setEmail(listResult.get(i).get(9));
                fileReqDto.setAddr1(listResult.get(i).get(10));
                fileReqDto.setTell(listResult.get(i).get(11));
                fileReqDto.setBirth(listResult.get(i).get(12));
                fileReqDto.setClassDate(listResult.get(i).get(13));

                int cnt = studentMapper.excelUpload(fileReqDto);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e);
        }
    }
}
