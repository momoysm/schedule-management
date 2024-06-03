package com.sparta.schedulemanagement.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulePasswordRequestDto {

    @NotNull
    private Long id;

    @NotBlank
    private String password;

}
