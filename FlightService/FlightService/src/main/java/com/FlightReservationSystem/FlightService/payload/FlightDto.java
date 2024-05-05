package com.FlightReservationSystem.FlightService.payload;

import com.FlightReservationSystem.FlightService.customValidations.annotations.InvalidTravelClass;
import com.FlightReservationSystem.FlightService.customValidations.annotations.NotBlankFields;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NotBlankFields
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
    @InvalidTravelClass
    private String travelClass;
    private int pricePerPerson;
    private int availableSeats;
}
