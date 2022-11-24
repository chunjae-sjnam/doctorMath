package com.chunjae.doctormath.main.operation.classManagement.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClassAddReqDto {

    private int seq = 0;
    private String className; // 클래스명
    private String curri; // 교육과정
    private String Grade; // 학년
    private String studentCnt;    // 학생수
    private String hakwonCode;    // 학원코드
    private String teacherCode;   // 메인선생님코드
    private String subTeacherCode;    // 보조선생님코드
    private List<String> studentCodeList;   // 학생목록

}
