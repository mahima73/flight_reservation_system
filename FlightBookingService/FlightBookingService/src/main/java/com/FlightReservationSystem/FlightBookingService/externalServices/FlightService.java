package com.FlightReservationSystem.FlightBookingService.externalServices;

import com.FlightReservationSystem.FlightBookingService.payload.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "FLIGHTSERVICE")
public interface FlightService {
    @GetMapping("/flights/{field}/{offset}/{pageSiz}")
    public List<FlightDto> getAllFlightsHandler(@PathVariable String field, @PathVariable int offset, @PathVariable int pageSiz);

    @GetMapping("/flights/{flightId}")
    FlightDto getSingleFlight(@PathVariable String flightId);

    @PutMapping("/flights/{flightId}/{noOfSeats}")
    FlightDto updateFlightInfo(@PathVariable String flightId, @PathVariable int noOfSeats);


}
