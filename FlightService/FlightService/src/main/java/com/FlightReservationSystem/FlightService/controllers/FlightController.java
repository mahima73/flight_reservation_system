package com.FlightReservationSystem.FlightService.controllers;
import com.FlightReservationSystem.FlightService.payload.FlightDto;
import com.FlightReservationSystem.FlightService.services.FlightService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightDto> addNewFlight(@Valid @RequestBody FlightDto fDto) throws Exception{
        System.out.println("flight added");
        FlightDto flightDto = flightService.createFlight(fDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(flightDto);
    }
    
    @GetMapping("/{field}/{offset}/{pageSiz}")
    public ResponseEntity<List<FlightDto>> getAllFlightsHandler(@PathVariable String field, @PathVariable int offset, @PathVariable int pageSiz){
        List<FlightDto> flightDtos = flightService.getAllFlightDtos(field,offset,pageSiz);
        return ResponseEntity.ok(flightDtos);
    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> searchFlightsWithToFromLocation(@RequestParam String source,
                                                                           @RequestParam String destination,
                                                                           @RequestParam LocalDate departDate,
                                                                           @RequestParam LocalDate arrivDate){
        System.out.println("response came heree");
        List<FlightDto> resultFlightDto = flightService.getSearchedFlights(source,destination,departDate,arrivDate);
        return ResponseEntity.status(HttpStatus.OK).body(resultFlightDto);
    }

    @GetMapping("/{fligtId}")
    public ResponseEntity<FlightDto> fetchSingleFlightById(@PathVariable String fligtId){
        FlightDto result = flightService.getSingleFlight(fligtId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{flightId}/{noOfSeats}")
    public ResponseEntity<FlightDto> updateFlightInfo(@PathVariable String flightId, @PathVariable int noOfSeats){
        FlightDto flightDto = flightService.updateFlightInfo(flightId,noOfSeats);
        return ResponseEntity.status(HttpStatus.OK).body(flightDto);
    }

}
