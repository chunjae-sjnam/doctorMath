package com.chunjae.doctormath.main.operation.attendance.service;

import com.chunjae.doctormath.main.operation.attendance.dto.request.AttendanceClassReqDto;
import com.chunjae.doctormath.main.operation.attendance.dto.request.AttendanceClassStudentReqDto;
import com.chunjae.doctormath.main.operation.attendance.dto.response.AttendanceClassResDto;
import com.chunjae.doctormath.main.operation.attendance.dto.response.AttendanceClassStudentResDto;
import com.chunjae.doctormath.main.operation.attendance.mapper.AttendanceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceService {

    private final AttendanceMapper attendanceMapper;

    // 출결관리 클래스 목록
    public List<AttendanceClassResDto> attendanceClassList(AttendanceClassReqDto attendanceClassReqDto) {
        return attendanceMapper.attendanceClassList(attendanceClassReqDto);
    }

    // 출결관리 클래스 학생 목록
    public List<AttendanceClassStudentResDto> attendanceClassStudentList(AttendanceClassStudentReqDto attendanceClassStudentReqDto) {
        return attendanceMapper.attendanceClassStudentList(attendanceClassStudentReqDto);
    }
}
