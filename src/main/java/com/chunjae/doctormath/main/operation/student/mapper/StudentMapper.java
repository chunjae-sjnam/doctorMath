package com.chunjae.doctormath.main.operation.student.mapper;

import com.chunjae.doctormath.main.operation.student.dto.request.FileReqDto;
import com.chunjae.doctormath.main.operation.student.dto.request.SeqReqDto;
import com.chunjae.doctormath.main.operation.student.dto.request.StudentReqDto;
import com.chunjae.doctormath.main.operation.student.dto.response.StudentResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    
    //학생 리스트
    List<StudentResDto> studentList(StudentReqDto studentReqDto);

    //학생 등록 - MEM_Student
    int insertMemStudent(StudentReqDto studentReqDto) throws Exception;

    //학생 등록 - MEM_Member
    int insertMemMemberStudent(StudentReqDto studentReqDto) throws Exception;

    //학부모 등록 - MEM_Member
    int insertMemMemberParent(StudentReqDto studentReqDto) throws Exception;

    //학생등록 - t_mem_parents
    int insertMemParents(StudentReqDto studentReqDto) throws Exception;

    //학생,학부모 seq 조회
    String selectSeq(SeqReqDto seqReqDto) throws Exception;

    //학생,학부모 seq 수정
    int updateSeq(SeqReqDto seqReqDto) throws Exception;

    //학생,학부모 seq 추가
    int insertSeq(SeqReqDto seqReqDto) throws Exception;

    //학생 상세 정보
    StudentResDto detailStudent(StudentReqDto studentReqDto) throws Exception;

    //학생 상세 정보 수정
    int updateStudent(StudentReqDto studentReqDto) throws Exception;

    //시도 리스트
    ArrayList<String> sidoList() throws Exception;

    //파일 업로드 DB 저장
    int excelUpload(FileReqDto fileReqDto) throws Exception;
}
