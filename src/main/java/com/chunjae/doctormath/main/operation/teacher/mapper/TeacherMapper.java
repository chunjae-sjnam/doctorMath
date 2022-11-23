package com.chunjae.doctormath.main.operation.teacher.mapper;

import com.chunjae.doctormath.main.operation.student.dto.request.SeqReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherDetailReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherModifyReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherSaveReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.response.TeacherDetailResDto;
import com.chunjae.doctormath.main.operation.teacher.dto.response.TeacherResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {

    // 선생님관리 목록
    List<TeacherResDto> teacherList(TeacherReqDto teacherReqDto);

    // 선생님 상세정보
    TeacherDetailResDto teacherDetail(TeacherDetailReqDto teacherDetailReqDto);

    // 선생님 수정
    int updateMemMasterTeacher(TeacherModifyReqDto teacherDetailReqDto);

    // 선생님 수정
    int updateMemTeacher(TeacherModifyReqDto teacherDetailReqDto);

    // 선생님 seq 조회
    String selectSeq(SeqReqDto seqReqDto);

    // 선생님 seq 수정
    int updateSeq(SeqReqDto seqReqDto);

    // 선생님 seq 추가
    int insertSeq(SeqReqDto seqReqDto);

    // 선생님 등록 - MEM_Member
    int insertMemMemberTeacher(TeacherSaveReqDto teacherSaveReqDto);

    // 선생님 등록 - MEM_Teacher
    int insertMemTeacher(TeacherSaveReqDto teacherSaveReqDto);
}
