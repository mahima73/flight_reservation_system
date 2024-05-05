package com.flightbookingSystem.userService.externalService;

import com.flightbookingSystem.userService.entities.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "FLIGHTSERVICE")
public interface FlightService {
    @GetMapping("/flights")
    List<FlightDto> searchFlightsWithToFromLocation(@RequestParam String source, @RequestParam String destination,
                                                    @RequestParam LocalDate departDate, @RequestParam LocalDate arrivDate);

}
