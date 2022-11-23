package com.chunjae.doctormath.main.operation.classManagement.dto.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassSearchListResDto {

    private String seq; // 클래스 번호(PK)
    private String className;   // 클래스 이름

}
