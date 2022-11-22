package com.chunjae.doctormath.main.operation.attendance.controller;

import com.chunjae.doctormath.main.operation.attendance.dto.request.AttendanceClassReqDto;
import com.chunjae.doctormath.main.operation.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("attendance/")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @RequestMapping("list-view")
    public String attendanceList(Model model) {

        model.addAttribute("classList",attendanceService.attendanceClassList(new AttendanceClassReqDto("H0003032")));

        return "main/attendance/attendanceList";
    }

}
