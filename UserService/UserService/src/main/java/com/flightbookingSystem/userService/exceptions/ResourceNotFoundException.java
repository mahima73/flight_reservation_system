package com.flightbookingSystem.userService.exceptions;

import org.springframework.http.ResponseEntity;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(){
        super("Not found issue");
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
