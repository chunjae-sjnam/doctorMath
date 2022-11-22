package com.chunjae.doctormath.main.operation.teacher.mapper;

import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherDetailReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherModifyReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherReqDto;
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
}
