package com.chunjae.doctormath.main.operation.student.service;

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

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class StudentService {

    private final StudentMapper studentMapper;

    public Map<String, Object> getDetailList(Map<String, Object> param) throws Exception {

        Map<String,Object> resultMap = new HashMap<>();
        List<Map<String,Object>> detailList = new ArrayList<>();

        detailList = studentMapper.getDetailList(param);
        resultMap.put("detailList", detailList);
        System.out.println("resultMap>>>>>" + resultMap);

        return resultMap;
    }

    public int updateList(Map<String, Object> param) throws Exception{
        return studentMapper.updateList(param);
    }

    public void fileUpload(File destFile) throws Exception{

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

            for (int i = 0; i < listResult.size(); i++){
                Map<String, Object> listMap = new HashMap<>();

                listMap.put("ClassName", listResult.get(i).get(0));
                listMap.put("Name", listResult.get(i).get(1));
                listMap.put("Curri", listResult.get(i).get(2));
                listMap.put("Grade", listResult.get(i).get(3));
                listMap.put("Status", listResult.get(i).get(4));
                listMap.put("SHtell", listResult.get(i).get(5));
                listMap.put("PHtell", listResult.get(i).get(6));
                listMap.put("SchoolName", listResult.get(i).get(7));
                listMap.put("Email", listResult.get(i).get(8));
                listMap.put("Addr1", listResult.get(i).get(9));
                listMap.put("Tell", listResult.get(i).get(10));
                listMap.put("Birth", listResult.get(i).get(11));
                listMap.put("CreDate", listResult.get(i).get(12));

                System.out.println("listMap" + listMap);
                studentMapper.insert(listMap); //DB insert
            }
        } catch (Exception e){
            log.error(e.getMessage());
            throw new Exception(e);
        }
    }

    public List<StudentResDto> getList(StudentReqDto studentReqDto) {
        return studentMapper.getList(studentReqDto);
    }
}
