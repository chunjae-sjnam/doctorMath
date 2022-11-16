package com.chunjae.doctormath.main.operation.student.mapper;

import com.chunjae.doctormath.main.operation.student.dto.request.StudentReqDto;
import com.chunjae.doctormath.main.operation.student.dto.response.StudentResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    
    //학생 리스트
    List<StudentResDto> getList(StudentReqDto studentReqDto);

    //학생 등록
    public void register(StudentReqDto studentReqDto) throws Exception;

    //seq 조회
    public String selectSeq() throws Exception;

    //seq 수정
    public void updateSeq() throws Exception;

    //seq 추가
    public void insertSeq() throws Exception;
    
    //code 조회
    public String selectCode(StudentReqDto studentReqDto) throws Exception;

    //학생 ID 조회
    public String selectID() throws Exception;

    //학생 상세 정보
    StudentResDto getDetail(StudentReqDto studentReqDto) throws Exception;

    //학생 상세 정보 수정
    int update(Map<String, Object> param) throws Exception;

    //시도 리스트
    List<StudentResDto> getSido() throws Exception;

    //파일 업로드 DB 저장
    public void insert(Map<String, Object> listMap) throws Exception;
}
