package com.chunjae.doctormath.main.operation.attendance.dto.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AttendanceClassResDto {

    private String classCode;   // 클래스 코드 PK
    private String className;   // 클래스 이름
    private String studentCnt;  // 클래스 학생 수

}
