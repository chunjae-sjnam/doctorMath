package com.chunjae.doctormath.main.operation.classManagement.mapper;

import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassManagementMapper {

    // 클래스관리 목록
    List<ClassResDto> classManagementList(ClassReqDto classReqDto);

    // 선생님 검색
    List<ClassTeacherSearchResDto> classSearchTeacherList(ClassTeacherSearchReqDto classTeacherSearchReqDto);

    // 팝업 - 학생선택 클래스 목록
    ClassSearchListResDto classSearchPopupList(ClassAcademyReqDto classAcademyReqDto);

    // 팝업 - 학생선택 클래스선택 - 학생목록
    ClassSearchListResDto classStudentSearchPopupList(ClassSeqReqDto classSeqReqDto);
}
