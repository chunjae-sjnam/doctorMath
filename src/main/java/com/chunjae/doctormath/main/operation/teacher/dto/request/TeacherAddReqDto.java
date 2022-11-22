package com.chunjae.doctormath.main.operation.teacher.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class TeacherAddReqDto {

    private String name;
    private String userId;
    private String tell;

}
