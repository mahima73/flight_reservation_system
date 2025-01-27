package com.FlightReservationSystem.FlightBookingService.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SeatsNotAvailableException.class)
    public Map<String,String> unavailableSeatsExceptionHandler(SeatsNotAvailableException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("message",ex.getMessage());
        return errorMap;
    }
}
