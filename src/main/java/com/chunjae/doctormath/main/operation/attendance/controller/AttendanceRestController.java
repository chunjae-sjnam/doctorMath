package com.chunjae.doctormath.main.operation.attendance.controller;

import com.chunjae.doctormath.main.operation.attendance.dto.request.AttendanceClassReqDto;
import com.chunjae.doctormath.main.operation.attendance.dto.request.AttendanceClassStudentReqDto;
import com.chunjae.doctormath.main.operation.attendance.dto.response.AttendanceClassResDto;
import com.chunjae.doctormath.main.operation.attendance.dto.response.AttendanceClassStudentResDto;
import com.chunjae.doctormath.main.operation.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/attendance/")
public class AttendanceRestController {

    private final AttendanceService attendanceService;

    // 출결관리 클래스 목록
    @RequestMapping("class-list")
    public List<AttendanceClassResDto> attendanceClassList(AttendanceClassReqDto attendanceClassReqDto) {
        return attendanceService.attendanceClassList(new AttendanceClassReqDto("H0003032"));
    }

    // 출결관리 클래스 학생 목록
    @RequestMapping("class-student-list")
    public List<AttendanceClassStudentResDto> attendanceClassStudentList(AttendanceClassStudentReqDto attendanceClassStudentReqDto) {
        return attendanceService.attendanceClassStudentList(attendanceClassStudentReqDto);
    }


}
