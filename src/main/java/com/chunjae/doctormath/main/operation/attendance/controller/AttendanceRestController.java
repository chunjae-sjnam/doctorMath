package com.chunjae.doctormath.main.operation.attendance.controller;

import com.chunjae.doctormath.main.operation.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/attendance/")
public class AttendanceRestController {

    private final AttendanceService attendanceService;

}
