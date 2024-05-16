package com.sparta.schedulemanagement.exception;

import com.sparta.schedulemanagement.dto.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exception {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDto> handleException(IllegalArgumentException exception){
        String message = exception.getMessage();

        ExceptionDto exceptionDto = new ExceptionDto(message);

        ResponseEntity<ExceptionDto> responseEntity = ResponseEntity.badRequest().body(exceptionDto);

        return responseEntity;
    }
}
