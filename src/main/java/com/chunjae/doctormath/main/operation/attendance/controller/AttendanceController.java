package com.chunjae.doctormath.main.operation.attendance.controller;

import com.chunjae.doctormath.main.operation.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("attendance/")
public class AttendanceController {

    private final AttendanceService attendanceService;

}
