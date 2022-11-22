package com.chunjae.doctormath.main.operation.teacher.controller;

import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherAddReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherDetailReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherModifyReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.request.TeacherReqDto;
import com.chunjae.doctormath.main.operation.teacher.dto.response.TeacherDetailResDto;
import com.chunjae.doctormath.main.operation.teacher.dto.response.TeacherResDto;
import com.chunjae.doctormath.main.operation.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/teacher/")
public class TeacherRestController {

    private final TeacherService teacherService;

    // 선생님관리 목록
    @RequestMapping("list")
    public List<TeacherResDto> teacherList(TeacherReqDto teacherReqDto) {
        return teacherService.teacherList(teacherReqDto);
    }

    // 선생님 상세정보
    @RequestMapping("detail")
    public TeacherDetailResDto teacherDetail(TeacherDetailReqDto teacherDetailReqDto) {
        return teacherService.teacherDetail(teacherDetailReqDto);
    }

    // 선생님 수정
    @RequestMapping("modify")
    public int updateTeacher(TeacherModifyReqDto teacherDetailReqDto) {
        return teacherService.updateTeacher(teacherDetailReqDto);
    }

    // 선생님 등록
    @RequestMapping("insert")
    public int insertTeacher(TeacherAddReqDto teacherAddReqDto) {
        log.info("insertTeacher teacherAddReqDto ==> {}", teacherAddReqDto);
        return 0;
    }

}
