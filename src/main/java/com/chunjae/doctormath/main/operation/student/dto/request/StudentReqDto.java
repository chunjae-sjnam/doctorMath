package com.chunjae.doctormath.main.operation.student.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentReqDto {

    private String HakwonCode;  //학원코드
    private String TeacherCode; //교사코드
    private String ClassCode;   //클래스코드
    private String keyword;     //검색어
}
