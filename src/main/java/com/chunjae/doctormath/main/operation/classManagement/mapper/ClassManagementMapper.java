package com.chunjae.doctormath.main.operation.classManagement.mapper;

import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassAcademyReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassSeqReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassResDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassSearchListResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassManagementMapper {

    // 클래스관리 목록
    List<ClassResDto> classManagementList(ClassAcademyReqDto classAcademyReqDto);

    // 팝업 - 학생선택 클래스 목록
    ClassSearchListResDto classSearchPopupList(ClassAcademyReqDto classAcademyReqDto);

    // 팝업 - 학생선택 클래스선택 - 학생목록
    ClassSearchListResDto classStudentSearchPopupList(ClassSeqReqDto classSeqReqDto);
}
