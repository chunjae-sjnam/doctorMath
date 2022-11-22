package com.chunjae.doctormath.main.operation.classManagement.dto.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassResDto {

    private String seq;
    private String className;   // 클래스 이름
    private String studentCount;   // 클래스 학생 수
    private String mainTeacherCode;    // 메인선생님 코드
    private String mainTeacherName;    // 선생님이름
    private String subTeacherCode;    // 서브선생님 코드
    private String subTeacherName;    // 선생님이름

}
