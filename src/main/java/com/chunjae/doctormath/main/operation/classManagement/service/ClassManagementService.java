package com.chunjae.doctormath.main.operation.classManagement.service;

import com.chunjae.doctormath.main.operation.classManagement.dto.request.*;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.*;
import com.chunjae.doctormath.main.operation.classManagement.mapper.ClassManagementMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassManagementService {

    private final ClassManagementMapper classManagementMapper;

    // 클래스관리 목록
    public List<ClassResDto> selectClassManagementList(ClassAcademyReqDto classAcademyReqDto) {
        return classManagementMapper.selectClassManagementList(classAcademyReqDto);
    }

    // 선생님 검색
    public List<ClassTeacherSearchResDto> selectClassSearchTeacherList(ClassTeacherSearchReqDto classTeacherSearchReqDto) {
        return classManagementMapper.selectClassSearchTeacherList(classTeacherSearchReqDto);
    }

    // 팝업 - 학생선택 클래스 목록
    public List<ClassSearchListResDto> selectClassSearchPopupList(ClassAcademyReqDto classAcademyReqDto) {
        return classManagementMapper.selectClassSearchPopupList(classAcademyReqDto);
    }

    // 팝업 - 학생선택 클래스선택 - 학생목록
    public List<ClassSearchListResDto> selectClassStudentSearchPopupList(ClassSeqReqDto classSeqReqDto) {
        return classManagementMapper.selectClassStudentSearchPopupList(classSeqReqDto);
    }

    // 학원 클래스별 학생 수
    public ClassSchoolGradeCountResDto selectClassSchoolGradeCount(ClassSchoolGradeReqDto classSchoolGradeReqDto) {
        return classManagementMapper.selectClassSchoolGradeCount(classSchoolGradeReqDto);
    }

    // 학원 클래스의 학년 학생목록
    public List<ClassSchoolGradeListResDto> selectClassSchoolGradeList(ClassSchoolGradeReqDto classSchoolGradeReqDto) {
        return classManagementMapper.selectClassSchoolGradeList(classSchoolGradeReqDto);
    }

    @Transactional
    public int insertMemClass(ClassAddReqDto classAddReqDto) {

        // ClassInsertTests에서 들어가는 데이터 테스트코드를 참조
        int insertMemClass = classManagementMapper.insertMemClass(classAddReqDto);  // MEM_Class 등록

        if (insertMemClass == 0 || classAddReqDto.getSeq() == 0 || classAddReqDto.getTeacherCode() == null) {
            return -1;
        }

        List<ClassStudentAddReqDto> classStudentAddReqDto = new ArrayList<>();
        int insertMemClassStudentsResCount = 0;
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (classAddReqDto.getStudentCodeList().size() != 0) {
            for (int i = 0; i < classAddReqDto.getStudentCodeList().size(); i++) {
                classStudentAddReqDto.add(new ClassStudentAddReqDto(classAddReqDto.getSeq(), classAddReqDto.getStudentCodeList().get(i), dateTime));
            }
            insertMemClassStudentsResCount = classManagementMapper.insertMemClassStudents(classStudentAddReqDto);   // MEM_Class_StudentList 등록
        }

        if (classAddReqDto.getStudentCodeList().size() != insertMemClassStudentsResCount) {
            return -1;
        }

        classManagementMapper.insertMemClassTeacher(new ClassTeacherAddReqDto(classAddReqDto.getSeq(), classAddReqDto.getTeacherCode(), dateTime));
        if (classAddReqDto.getSubTeacherCode() != null) {   // 보조선생님이 있다면 등록
            classManagementMapper.insertMemClassTeacher(new ClassTeacherAddReqDto(classAddReqDto.getSeq(), classAddReqDto.getSubTeacherCode(), dateTime));
        }

        return insertMemClass;
    }
}
