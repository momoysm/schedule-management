package com.sparta.schedulemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SchedulePasswordRequestDto {

    @NotBlank
    private Long scheduleId;

    @NotBlank
    private String password;

}
