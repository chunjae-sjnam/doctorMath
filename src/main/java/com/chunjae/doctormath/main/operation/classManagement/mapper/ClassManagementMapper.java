package com.chunjae.doctormath.main.operation.classManagement.mapper;

import com.chunjae.doctormath.main.operation.classManagement.dto.request.*;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassManagementMapper {

    // 클래스관리 목록
    List<ClassResDto> selectClassManagementList(ClassAcademyReqDto classAcademyReqDto);

    // 선생님 검색
    List<ClassTeacherSearchResDto> selectClassSearchTeacherList(ClassTeacherSearchReqDto classTeacherSearchReqDto);

    // 팝업 - 학생선택 클래스 목록
    List<ClassSearchListResDto> selectClassSearchPopupList(ClassAcademyReqDto classAcademyReqDto);

    // 팝업 - 학생선택 클래스선택 - 학생목록
    List<ClassSearchListResDto> selectClassStudentSearchPopupList(ClassSeqReqDto classSeqReqDto);

    // 학원 클래스별 학생 수
    ClassSchoolGradeCountResDto selectClassSchoolGradeCount(ClassSchoolGradeReqDto classSchoolGradeReqDto);

    // 학원 클래스의 학년 학생목록
    List<ClassSchoolGradeListResDto> selectClassSchoolGradeList(ClassSchoolGradeReqDto classSchoolGradeReqDto);

    // 클래스 등록
    int insertMemClass(ClassAddReqDto classAddReqDto);

    // 클래스에 소속된 학생등록
    int insertMemClassStudents(@Param("requests") List<ClassStudentAddReqDto> classStudentAddReqDtos);

    // 클래스에 소속된 선생등록
    int insertMemClassTeacher(ClassTeacherAddReqDto classTeacherAddReqDto);
}
