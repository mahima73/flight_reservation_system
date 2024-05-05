package com.FlightReservationSystem.FlightBookingService.payload;

import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {
    private String fromLocation;
    private String toLocation;
    @Future
    private LocalDate departureDate;
    @Future
    private LocalDate arrivalDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String flightName;
    private String travelClass;
    private int pricePerPerson;
    private int availableSeats;
}