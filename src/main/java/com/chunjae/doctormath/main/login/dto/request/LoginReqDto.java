package com.chunjae.doctormath.main.login.dto.request;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqDto {

    private String userId;  // 아이디
    private String password;    // 비밀번호

}
