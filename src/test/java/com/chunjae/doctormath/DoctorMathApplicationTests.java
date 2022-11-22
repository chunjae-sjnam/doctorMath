package com.chunjae.doctormath;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DoctorMathApplicationTests {

    @Test
    @DisplayName("테스트")
    void test() {

        String s = "S";
        String inputNumber = "50947";

        String result = s + inputNumber;
        System.out.println("result ==> " + result);

    }


}
