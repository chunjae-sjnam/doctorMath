package com.chunjae.doctormath.main.operation.teacher.controller;

import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.response.TeacherResDto;
import com.chunjae.doctormath.main.operation.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/teacher/")
public class TeacherController {

    private final TeacherService teacherService;

    // 선생님 관리
    @RequestMapping("list-view")
    public String teacherView(Model model) {
        List<TeacherResDto> teacherResDto = teacherService.teacherList(new TeacherReqDto("H00000001"));
        log.info("teacherResDto ==> {}", teacherResDto);
        model.addAttribute("teacherList", teacherResDto);
        return "main/teacher/teacherList";
    }


}
