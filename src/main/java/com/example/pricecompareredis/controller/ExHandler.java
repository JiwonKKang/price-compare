package com.example.pricecompareredis.controller;

import com.example.pricecompareredis.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> notFoundExHandler(NotFoundException ex) {
        return new ResponseEntity<>(ex.getErrMsg(), ex.getHttpStatus());
    }
}
