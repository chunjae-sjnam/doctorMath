package com.chunjae.doctormath.main.operation.teacher.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class TeacherModifyReqDto {

    private String code;    // 선생님 코드
    private String name;    // 이름
    private String tell;    // 연락처

}
