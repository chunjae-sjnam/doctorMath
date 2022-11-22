package com.chunjae.doctormath.main.operation.classManagement.mapper;

import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassManagementMapper {

    // 클래스관리 목록
    List<ClassResDto> classManagementList(ClassReqDto classReqDto);
}
