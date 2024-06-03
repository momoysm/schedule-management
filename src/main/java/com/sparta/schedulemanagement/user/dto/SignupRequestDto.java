package com.sparta.schedulemanagement.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank(message = "별명을 입력하세요.")
    private String nickname;
    @NotBlank(message = "아이디를 입력하세요.")
    private String username;
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 8, max = 15, message = "비밀번호는 8자에서 15자 사이입니다.")
    @Pattern(regexp = "[a-zA-Z0-9]*$", message = "비밀번호 형식이 일치하지 않습니다.")
    private String password;

    private boolean admin = false;
    private String adminToken = "";

}
