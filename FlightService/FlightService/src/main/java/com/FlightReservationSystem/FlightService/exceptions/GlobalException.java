package com.FlightReservationSystem.FlightService.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IdenticalSourceAndDestinationException.class)
    public Map<String,String> identicalSourceAndDestinationExceptionHandler(IdenticalSourceAndDestinationException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Error message",ex.getMessage());
        errorMap.put("Status", String.valueOf(HttpStatus.BAD_REQUEST));
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String>  invalidInputExceptionHandler(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error)-> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String,String> illegalArgumentExceptionHandler(IllegalArgumentException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("message",ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String,String> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("message",ex.getMessage());
        return errorMap;
    }
}
