package com.FlightReservationSystem.FlightService.exceptions;

public class IdenticalSourceAndDestinationException extends Exception{
    public IdenticalSourceAndDestinationException(){
        super("Invalid Input passed. Please check again!");
    }
    public IdenticalSourceAndDestinationException(String fromPlace , String toPlace){
        super("Invalid Input passed " + fromPlace + " and " + toPlace + " can't be same!");
    }
}
