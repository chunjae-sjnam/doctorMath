package com.chunjae.doctormath.main.operation.classManagement.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClassStudentAddReqDto {

    private int seq;
    private String studentCode;
    private String createDate;

}
