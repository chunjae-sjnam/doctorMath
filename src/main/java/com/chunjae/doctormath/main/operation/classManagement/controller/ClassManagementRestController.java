package com.chunjae.doctormath.main.operation.classManagement.controller;

import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassTeacherSearchReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassResDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassTeacherSearchResDto;
import com.chunjae.doctormath.main.operation.classManagement.service.ClassManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/class/")
public class ClassManagementRestController {

    private final ClassManagementService classManagementService;

    // 클래스관리 목록
    @RequestMapping("list")
    public List<ClassResDto> classManagementList(ClassReqDto classReqDto) {

        //temp
        classReqDto.setHakwonCode("H1000000");
        return classManagementService.classManagementList(classReqDto);
    }

    // 선생님 검색
    @RequestMapping("class-search-teacher-list")
    public List<ClassTeacherSearchResDto> classSearchTeacherList(ClassTeacherSearchReqDto classTeacherSearchReqDto){
        return classManagementService.classSearchTeacherList(classTeacherSearchReqDto);
    }
}
