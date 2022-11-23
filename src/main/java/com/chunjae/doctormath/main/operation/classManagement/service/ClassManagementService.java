package com.chunjae.doctormath.main.operation.classManagement.service;

import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassAcademyReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassSeqReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassTeacherSearchReqDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassResDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassSearchListResDto;
import com.chunjae.doctormath.main.operation.classManagement.dto.response.ClassTeacherSearchResDto;
import com.chunjae.doctormath.main.operation.classManagement.mapper.ClassManagementMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassManagementService {

    private final ClassManagementMapper classManagementMapper;

    // 클래스관리 목록
    public List<ClassResDto> classManagementList(ClassAcademyReqDto classAcademyReqDto) {
        return classManagementMapper.classManagementList(classAcademyReqDto);
    }

    // 선생님 검색
    public List<ClassTeacherSearchResDto> classSearchTeacherList(ClassTeacherSearchReqDto classTeacherSearchReqDto) {
        return classManagementMapper.classSearchTeacherList(classTeacherSearchReqDto);
    }

    // 팝업 - 학생선택 클래스 목록
    public ClassSearchListResDto classSearchPopupList(ClassAcademyReqDto classAcademyReqDto) {
        return classManagementMapper.classSearchPopupList(classAcademyReqDto);
    }

    // 팝업 - 학생선택 클래스선택 - 학생목록
    public ClassSearchListResDto classStudentSearchPopupList(ClassSeqReqDto classSeqReqDto) {
        return classManagementMapper.classStudentSearchPopupList(classSeqReqDto);
    }
}
