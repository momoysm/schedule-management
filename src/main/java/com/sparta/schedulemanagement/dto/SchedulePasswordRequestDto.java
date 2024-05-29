package com.sparta.schedulemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulePasswordRequestDto {

    @NotBlank
    private Long id;

    @NotBlank
    private String password;

}
