package com.chunjae.doctormath.main.operation.student.mapper;

import com.chunjae.doctormath.main.operation.student.dto.request.StudentReqDto;
import com.chunjae.doctormath.main.operation.student.dto.response.StudentResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    //학생 상세 정보
    List<Map<String, Object>> getDetailList(Map<String, Object> param) throws Exception;

    //학생 상세 정보 수정
    int updateList(Map<String, Object> param) throws Exception;

    //시도 리스트
    List<Map<String, Object>> sidoList() throws Exception;

    //구군 리스트
    List<Map<String, Object>> guList(Map<String, Object> param) throws Exception;

    //파일 업로드 DB 저장
    public void insert(Map<String, Object> listMap) throws Exception;

    List<StudentResDto> getList(StudentReqDto studentReqDto);
}
