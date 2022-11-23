package com.chunjae.doctormath.main.operation.classManagement.controller;

import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassResDto;
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
    public String classView(ClassReqDto classReqDto, Model model){

        //temp
        classReqDto.setHakwonCode("H0000005");

        List<ClassResDto> classResDtos = classManagementService.classManagementList(classReqDto);
        model.addAttribute("classList", classResDtos);
        return "main/classManagement/classList";
    }

}