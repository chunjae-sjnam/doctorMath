package com.chunjae.doctormath.main.operation.classManagement.controller;

import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassAcademyReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassSeqReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassTeacherSearchReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassResDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassSearchListResDto;
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
    public List<ClassResDto> classManagementList(ClassAcademyReqDto classAcademyReqDto) {

        //temp
        classAcademyReqDto.setHakwonCode("H1000000");
        return classManagementService.classManagementList(classAcademyReqDto);
    }

    // 선생님 검색
    @RequestMapping("class-search-teacher-list")
    public List<ClassTeacherSearchResDto> classSearchTeacherList(ClassTeacherSearchReqDto classTeacherSearchReqDto){
        return classManagementService.classSearchTeacherList(classTeacherSearchReqDto);
    }

    // 팝업 - 학생선택 클래스 목록
    @RequestMapping("classs-search-popup-list")
    public ClassSearchListResDto classSearchPopupList(ClassAcademyReqDto classAcademyReqDto) {
        return classManagementService.classSearchPopupList(classAcademyReqDto);
    }

    // 팝업 - 학생선택 클래스선택 - 학생목록
    @RequestMapping("class-student-search-popup-list")
    public ClassSearchListResDto classStudentSearchPopupList(ClassSeqReqDto classSeqReqDto) {
        return classManagementService.classStudentSearchPopupList(classSeqReqDto);
    }
}
