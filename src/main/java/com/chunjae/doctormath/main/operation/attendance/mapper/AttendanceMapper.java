package com.chunjae.doctormath.main.operation.attendance.mapper;

import com.chunjae.doctormath.main.operation.attendance.dto.request.AttendanceClassReqDto;
import com.chunjae.doctormath.main.operation.attendance.dto.request.AttendanceClassStudentReqDto;
import com.chunjae.doctormath.main.operation.attendance.dto.response.AttendanceClassResDto;
import com.chunjae.doctormath.main.operation.attendance.dto.response.AttendanceClassStudentResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttendanceMapper {

    // 출결관리 클래스 목록
    List<AttendanceClassResDto> attendanceClassList(AttendanceClassReqDto attendanceClassReqDto);

    // 출결관리 클래스 학생 목록
    List<AttendanceClassStudentResDto> attendanceClassStudentList(AttendanceClassStudentReqDto attendanceClassStudentReqDto);
}
