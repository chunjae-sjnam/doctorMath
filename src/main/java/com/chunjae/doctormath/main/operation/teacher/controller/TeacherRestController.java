package com.chunjae.doctormath.main.operation.teacher.controller;

import com.chunjae.doctormath.main.operation.teacher.dto.request.*;
import com.chunjae.doctormath.main.operation.teacher.dto.response.TeacherDetailResDto;
import com.chunjae.doctormath.main.operation.teacher.dto.response.TeacherResDto;
import com.chunjae.doctormath.main.operation.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public int insertTeacher(TeacherSaveReqDto teacherSaveReqDto) throws Exception {
        log.info("insertTeacher teacherSaveReqDto ==> {}", teacherSaveReqDto);

        String teacherCode = teacherService.selectTeacherSeq();
        teacherSaveReqDto.setTeacherCode(teacherCode);

        //temp
        teacherSaveReqDto.setAuth("T");
        teacherSaveReqDto.setHakwonCode("H0000005");

        int a;
        int b;

        try {
            a = teacherService.insertMemMemberTeacher(teacherSaveReqDto);
            b = teacherService.insertMemTeacher(teacherSaveReqDto);

        } catch (Exception e) {
            log.error("insertTeacher Error : " + e.getMessage());
            return -1;
        }

        return a+b;
    }

}
