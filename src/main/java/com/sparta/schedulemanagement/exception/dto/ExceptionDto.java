package com.sparta.schedulemanagement.exception.dto;

import lombok.Getter;

@Getter
public class ExceptionDto {

    private String message;

    public ExceptionDto(String message) {
        this.message = message;
    }

}
