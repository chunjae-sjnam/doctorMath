package com.chunjae.doctormath.main.operation.teacher.service;

import com.chunjae.doctormath.main.operation.student.dto.request.SeqReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherDetailReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherModifyReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherSaveReqDto;
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

    // 선생님 seq 조회
    public String selectTeacherSeq() throws Exception {

        String seq = userKey("T");

        if(seq != null){
            teacherMapper.updateSeq(new SeqReqDto("T"));

        } else {
            teacherMapper.insertSeq(new SeqReqDto("T"));
        }

        seq = userKey("T");
        String str = "0000000" + seq;

        String teacherCode = "T" + str.substring(str.length()-7);
        log.info("teacherCode>>>>>>>>" + teacherCode);

        return teacherCode;
    }

    public String userKey(String key) throws Exception {
        return teacherMapper.selectSeq(new SeqReqDto(key));
    }

    // 선생님 등록 - MEM_Member
    public int insertMemMemberTeacher(TeacherSaveReqDto teacherSaveReqDto) {
        return teacherMapper.insertMemMemberTeacher(teacherSaveReqDto);
    }

    // 선생님 등록 - MEM_Teacher
    public int insertMemTeacher(TeacherSaveReqDto teacherSaveReqDto) {
        return teacherMapper.insertMemTeacher(teacherSaveReqDto);
    }
}
