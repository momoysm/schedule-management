package com.sparta.schedulemanagement.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequestDto {

    private String content;

    @NotBlank
    @Length(max = 200, message = "200자 이내여야 합니다.")
    private String title;

}
