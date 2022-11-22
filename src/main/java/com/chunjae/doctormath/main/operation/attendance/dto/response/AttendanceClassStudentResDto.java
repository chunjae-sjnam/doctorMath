package com.chunjae.doctormath.main.operation.attendance.dto.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AttendanceClassStudentResDto {

    private String studentCode; // 학생코드
    private String name;    // 학생이름
    private String pickupDate;  // 등원일자
    private String dropOffDate; // 하원일자

}
