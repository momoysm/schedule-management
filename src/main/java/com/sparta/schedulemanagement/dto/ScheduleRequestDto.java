package com.sparta.schedulemanagement.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private String scheduleTitle;
    private String scheduleContent;
    private String scheduleManager;
    private String password;

}
