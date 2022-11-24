package com.chunjae.doctormath.main.operation.classManagement.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClassSchoolGradeReqDto {

    private final String hakwonCode;
    private final String schoolCode;

}
