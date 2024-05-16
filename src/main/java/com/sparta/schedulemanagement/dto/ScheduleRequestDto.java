package com.sparta.schedulemanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class ScheduleRequestDto {

    private String scheduleContent;

    @NotBlank
    @Length(max = 200, message = "200자 이내여야 합니다.")
    private String scheduleTitle;

    @Email(message = "Email형식에 맞지 않습니다.")
    private String scheduleManager;

    @NotBlank
    private String password;

}
