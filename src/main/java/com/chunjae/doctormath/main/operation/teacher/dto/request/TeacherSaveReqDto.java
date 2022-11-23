package com.chunjae.doctormath.main.operation.teacher.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSaveReqDto {

    private String name;
    private String userId;
    private String tell;
    private String auth;
    private String teacherCode;
    private String hakwonCode;
}
