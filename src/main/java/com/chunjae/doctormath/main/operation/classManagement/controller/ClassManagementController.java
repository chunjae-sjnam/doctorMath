package com.chunjae.doctormath.main.operation.classManagement.controller;

import com.chunjae.doctormath.common.commonEnum.SchoolGrade;
import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassAcademyReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassSchoolGradeReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassResDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassSchoolGradeCountResDto;
import com.chunjae.doctormath.main.operation.classManagement.service.ClassManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/class/")
public class ClassManagementController {

    private final ClassManagementService classManagementService;

    // 클래스 관리
    @RequestMapping("list-view")
    public String classView(ClassAcademyReqDto classAcademyReqDto, Model model) {

        //temp
        classAcademyReqDto.setHakwonCode("H0000005");
        List<ClassResDto> classResDtos = classManagementService.selectClassManagementList(classAcademyReqDto);
        model.addAttribute("classList", classResDtos);

        ClassSchoolGradeCountResDto elementCount = classManagementService.selectClassSchoolGradeCount(new ClassSchoolGradeReqDto("H0000005", SchoolGrade.elementarySchool.getCode()));
        ClassSchoolGradeCountResDto middleCount = classManagementService.selectClassSchoolGradeCount(new ClassSchoolGradeReqDto("H0000005", SchoolGrade.middleSchool.getCode()));
        ClassSchoolGradeCountResDto highCount = classManagementService.selectClassSchoolGradeCount(new ClassSchoolGradeReqDto("H0000005", SchoolGrade.highSchool.getCode()));

        log.info("elementCount ==> {}",elementCount);
        log.info("middleCount ==> {}",middleCount);
        log.info("highCount ==> {}",highCount);

        return "main/classManagement/classList";
    }

}