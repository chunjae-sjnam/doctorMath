package com.chunjae.doctormath.main.operation.classManagement.mapper;

import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassTeacherSearchReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassResDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassTeacherSearchResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassManagementMapper {

    // 클래스관리 목록
    List<ClassResDto> classManagementList(ClassReqDto classReqDto);

    // 선생님 검색
    List<ClassTeacherSearchResDto> classSearchTeacherList(ClassTeacherSearchReqDto classTeacherSearchReqDto);
}
