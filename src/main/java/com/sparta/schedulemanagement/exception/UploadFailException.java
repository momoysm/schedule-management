package com.sparta.schedulemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UploadFailException extends RuntimeException {

    public UploadFailException(String message) {
        super(message);
    }

}
