package com.FlightReservationSystem.FlightBookingService.exceptions;

public class SeatsNotAvailableException extends RuntimeException{
    public SeatsNotAvailableException(String msg){
        super(msg);
    }
}
