package com.chunjae.doctormath.main.operation.attendance.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("attendance/")
public class AttendanceController {

    @RequestMapping("list-view")
    public String test() {
        return "main/attendance/attendanceList";
    }

}
