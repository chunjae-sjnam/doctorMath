package com.chunjae.doctormath.main.operation.classManagement.service;

import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassResDto;
import com.chunjae.doctormath.main.operation.classManagement.mapper.ClassManagementMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassManagementService {

    private final ClassManagementMapper classManagementMapper;

    // 클래스관리 목록
    public List<ClassResDto> classManagementList(ClassReqDto classReqDto) {
        return classManagementMapper.classManagementList(classReqDto);
    }
}
