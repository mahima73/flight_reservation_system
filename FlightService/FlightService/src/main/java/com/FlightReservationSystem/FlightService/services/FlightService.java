package com.FlightReservationSystem.FlightService.services;

import com.FlightReservationSystem.FlightService.exceptions.IdenticalSourceAndDestinationException;
import com.FlightReservationSystem.FlightService.payload.FlightDto;
import org.hibernate.dialect.pagination.FetchLimitHandler;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    //create Flight new
    FlightDto createFlight(FlightDto fDto) throws IdenticalSourceAndDestinationException;
    List<FlightDto> getAllFlightDtos(String field,int offset, int pageSiz);

    List<FlightDto> getSearchedFlights(String source, String destination, LocalDate departDate, LocalDate arrivDate);
    FlightDto getSingleFlight(String flightId);

    //update Flight information
    FlightDto updateFlightInfo(String flightId, int seats);

}
