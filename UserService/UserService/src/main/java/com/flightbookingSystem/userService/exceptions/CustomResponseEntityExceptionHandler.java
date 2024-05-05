package com.flightbookingSystem.userService.exceptions;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handlerMethodArgumentInvalid(MethodArgumentNotValidException ex){
        Map<String ,String > errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String ,String> handlerResourceNotFound(ResourceNotFoundException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("status", String.valueOf(HttpStatus.NOT_FOUND));
        errorMap.put("message", ex.getMessage());
        return errorMap;
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders header, HttpStatus stats, WebRequest rqst) {
//        return new ResponseEntity<>("Validation failed:" + ex.getBindingResult().getAllErrors(), HttpStatus.BAD_REQUEST);
//    }
}


