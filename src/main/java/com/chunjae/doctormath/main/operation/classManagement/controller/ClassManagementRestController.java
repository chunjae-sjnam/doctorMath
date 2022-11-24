package com.chunjae.doctormath.main.operation.classManagement.controller;

import com.chunjae.doctormath.main.operation.classManagement.dto.request.*;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassResDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassSchoolGradeListResDto;
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
    public List<ClassResDto> selectClassManagementList(ClassAcademyReqDto classAcademyReqDto) {

        //temp
        classAcademyReqDto.setHakwonCode("H1000000");
        return classManagementService.selectClassManagementList(classAcademyReqDto);
    }

    // -- 등록,수정 팝업을 열면서 ajax를 통해 아래의 데이터 호출 start --
    // 선생님 검색
    @RequestMapping("class-search-teacher-list")
    public List<ClassTeacherSearchResDto> selectClassSearchTeacherList(ClassTeacherSearchReqDto classTeacherSearchReqDto){
        return classManagementService.selectClassSearchTeacherList(classTeacherSearchReqDto);
    }

    // 팝업 - 학생선택 클래스 목록
    @RequestMapping("class-search-popup-list")
    public List<ClassSearchListResDto> selectClassSearchPopupList(ClassAcademyReqDto classAcademyReqDto) {
        return classManagementService.selectClassSearchPopupList(classAcademyReqDto);
    }

    // 팝업 - 학생선택 클래스선택 - 학생목록
    @RequestMapping("class-student-search-popup-list")
    public List<ClassSearchListResDto> selectClassStudentSearchPopupList(ClassSeqReqDto classSeqReqDto) {
        return classManagementService.selectClassStudentSearchPopupList(classSeqReqDto);
    }

    // 학원 클래스의 학년 학생목록
    @RequestMapping("class-school-grade-list")
    public List<ClassSchoolGradeListResDto> selectClassSchoolGradeList(ClassSchoolGradeReqDto classSchoolGradeReqDto) {
        return classManagementService.selectClassSchoolGradeList(classSchoolGradeReqDto);
    }
    // -- 등록,수정 팝업을 열면서 ajax를 통해 아래의 데이터 호출 end --

    @RequestMapping("insert-class")
    private int insertMemClass(ClassAddReqDto classAddReqDto) {
        return classManagementService.insertMemClass(classAddReqDto);
    }


}
