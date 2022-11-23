package com.chunjae.doctormath.main.operation.teacher.dto.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TeacherResDto {

    private String teacherCode;     // 선생님 코드
    private String name;            // 이름
    private String userId;          // 아이디
    private String tell;            // 연락처
    private String classNames;      // 담당클래스 목록
    private String teacherStudentCount; // 담당선생님의 클래스 학생 수

}
