package com.sparta.schedulemanagement.exception;

import com.sparta.schedulemanagement.dto.ExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger;

    public GlobalExceptionHandler() {
        this.logger = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDto> handleException(IllegalArgumentException exception){

        String message = exception.getMessage();

        ExceptionDto exceptionDto = new ExceptionDto(message);

        ResponseEntity<ExceptionDto> responseEntity = ResponseEntity.badRequest().body(exceptionDto);

        logger.error(exception.getMessage());

        return responseEntity;
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDto> handleException(NullPointerException exception){

        String message = exception.getMessage();

        ExceptionDto exceptionDto = new ExceptionDto(message);

        ResponseEntity<ExceptionDto> responseEntity = ResponseEntity.badRequest().body(exceptionDto);

        logger.error(exception.getMessage());

        return responseEntity;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleException(MethodArgumentNotValidException exception){

        String message = Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage();

        ExceptionDto exceptionDto = new ExceptionDto(message);

        ResponseEntity<ExceptionDto> responseEntity = ResponseEntity.badRequest().body(exceptionDto);

        logger.error(message);

        return responseEntity;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleException(RuntimeException exception){

        ExceptionDto exceptionDto = new ExceptionDto(exception.getMessage());

        ResponseEntity<ExceptionDto> responseEntity = ResponseEntity.badRequest().body(exceptionDto);

        logger.error(exception.getMessage());

        return responseEntity;
    }

}
