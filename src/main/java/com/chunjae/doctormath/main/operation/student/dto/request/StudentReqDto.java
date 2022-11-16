package com.chunjae.doctormath.main.operation.student.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentReqDto {

    private String id;
    private String hakwonCode;  //학원코드
    private String teacherCode; //교사코드
    private String studentCode; //학생코드
    private String classCode;   //클래스코드
    private String condition;   //검색조건
    private String keyword;     //검색어
    private String name;        //이름
    private String curri;       //학교급
    private String grade;       //학년
    private String status;      //상태
    private String shtell;      //학생 연락처
    private String phtell;      //학부모 연락처
    private String tell;        //집전화
    private String sido;        //시도
    private String gu;          //구군
    private String school;      //학교
    private String email;       //이메일
    private String postNum;     //우편번호
    private String addr;        //주소
    private String birth;       //생년월일
    private String classStart;  //수업 시작일
    private String memo;        //비고
}
