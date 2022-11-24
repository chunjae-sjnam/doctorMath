package com.chunjae.doctormath;

import com.chunjae.doctormath.main.operation.classManagement.dto.request.ClassAddReqDto;
import com.chunjae.doctormath.main.operation.classManagement.service.ClassManagementService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ClassInsertTests {

    @Autowired
    private ClassManagementService classManagementService;


    @Test
    @DisplayName("클래스 등록")
    void test() {
        List<ClassAddReqDto> dto = new ArrayList<>();
        // 테스트 케이스 데이터 (골라서 쓰자)
        dto.add(new ClassAddReqDto(0, "className", "M", "1", "10", "H0000005", "T0000001", "H0000005", Arrays.asList(null)));
        dto.add(new ClassAddReqDto(0, "className1", "E", "5", "2", "H0000005", "T0000001", "H0000005", Arrays.asList("S00000001", "S00000002", "S00000003")));
        dto.add(new ClassAddReqDto(0, "className2", "M", "1", "10", "H0000005", "T0000001", "H0000005", Arrays.asList("S00000001", "S00000002", "S00000003")));
        dto.add(new ClassAddReqDto(0, "className4", "M", "1", "10", "H0000005", "T0000001", "H0000005", Arrays.asList("S00000001", "S00000002", "S00000003")));

        classManagementService.insertMemClass(dto.get(0));

    }

}
