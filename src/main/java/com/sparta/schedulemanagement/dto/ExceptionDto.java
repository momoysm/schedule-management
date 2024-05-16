package com.sparta.schedulemanagement.dto;

import lombok.Getter;

@Getter
public class ExceptionDto {

    private String message;

    public ExceptionDto(String message) {
        this.message = message;
    }

}
