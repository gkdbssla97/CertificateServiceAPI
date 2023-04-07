package com.example.miniProj.util.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@Builder
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseEntity> handlerCustomExceptionHandler(Exception e) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("handler  : " + e.getMessage());

        ErrorResponseEntity errorResponseEntity = objectMapper.readValue(e.getMessage(), ErrorResponseEntity.class);
        System.out.println(errorResponseEntity.toString());
        return new ResponseEntity<>(errorResponseEntity, HttpStatus.BAD_REQUEST);
    }
}
