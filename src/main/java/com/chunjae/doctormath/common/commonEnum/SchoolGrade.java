package com.chunjae.doctormath.common.commonEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SchoolGrade {

    elementarySchool("E", "초등"),
    middleSchool("M", "중등"),
    highSchool("H", "고등");

    private final String code;
    private final String description;

}
