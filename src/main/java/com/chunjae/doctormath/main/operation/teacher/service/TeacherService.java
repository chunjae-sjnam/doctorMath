package com.chunjae.doctormath.main.operation.teacher.service;

import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherDetailReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherModifyReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.response.TeacherDetailResDto;
import com.chunjae.doctormath.main.operation.teacher.dto.response.TeacherResDto;
import com.chunjae.doctormath.main.operation.teacher.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherMapper teacherMapper;

    // 선생님관리 목록
    public List<TeacherResDto> teacherList(TeacherReqDto teacherReqDto) {
        log.info("teacherList teacherReqDto ==> {}", teacherReqDto);
        List<TeacherResDto> teacherResDtos = teacherMapper.teacherList(teacherReqDto);

        return teacherMapper.teacherList(teacherReqDto);
    }

    // 선생님 상세정보
    public TeacherDetailResDto teacherDetail(TeacherDetailReqDto teacherDetailReqDto) {
        return teacherMapper.teacherDetail(teacherDetailReqDto);
    }

    // 선생님 수정
    public int updateTeacher(TeacherModifyReqDto teacherDetailReqDto) {
        int a = teacherMapper.updateMemMasterTeacher(teacherDetailReqDto);
        int b = teacherMapper.updateMemTeacher(teacherDetailReqDto);
        return a + b;
    }
}
