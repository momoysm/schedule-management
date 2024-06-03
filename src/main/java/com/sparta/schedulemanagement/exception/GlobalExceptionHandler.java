package com.sparta.schedulemanagement.exception;

import com.sparta.schedulemanagement.exception.dto.ExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger;

    public GlobalExceptionHandler() {
        this.logger = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleException(NotFoundException exception){

        String message = exception.getMessage();

        ExceptionDto exceptionDto = new ExceptionDto(message);

        ResponseEntity<ExceptionDto> responseEntity = ResponseEntity.badRequest().body(exceptionDto);

        logger.error(exception.getMessage());

        return responseEntity;
    }

    @ExceptionHandler(UploadFailException.class)
    public ResponseEntity<ExceptionDto> handleException(UploadFailException exception){

        String message = exception.getMessage();

        ExceptionDto exceptionDto = new ExceptionDto(message);

        ResponseEntity<ExceptionDto> responseEntity = ResponseEntity.badRequest().body(exceptionDto);

        logger.error(exception.getMessage());

        return responseEntity;
    }

    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity<ExceptionDto> handleException(LoginFailException exception){

        String message = exception.getMessage();

        ExceptionDto exceptionDto = new ExceptionDto(message);

        ResponseEntity<ExceptionDto> responseEntity = ResponseEntity.badRequest().body(exceptionDto);

        logger.error(exception.getMessage());

        return responseEntity;
    }

}
