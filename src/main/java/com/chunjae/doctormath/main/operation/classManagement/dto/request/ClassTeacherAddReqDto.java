package com.chunjae.doctormath.main.operation.classManagement.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClassTeacherAddReqDto {

    private int seq;
    private String TeacherCode;
    private String createDate;

}
