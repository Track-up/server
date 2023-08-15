package com.gimnsio.libreta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
@ControllerAdvice
public class ApiRequestHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<?> handleApiRequestException(ApiRequestException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(e.getMessage(),
                badRequest,
                ZonedDateTime.now());
        return new ResponseEntity<>(apiException,badRequest);
    }

}
