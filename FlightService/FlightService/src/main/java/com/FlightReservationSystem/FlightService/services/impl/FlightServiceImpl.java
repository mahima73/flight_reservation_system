package com.FlightReservationSystem.FlightService.services.impl;

import com.FlightReservationSystem.FlightService.entities.Flight;
import com.FlightReservationSystem.FlightService.exceptions.IdenticalSourceAndDestinationException;
import com.FlightReservationSystem.FlightService.exceptions.ResourceNotFoundException;
import com.FlightReservationSystem.FlightService.payload.FlightDto;
import com.FlightReservationSystem.FlightService.repositories.FlightRepository;
import com.FlightReservationSystem.FlightService.services.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRepository flightRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public FlightDto createFlight(FlightDto fDto) throws IdenticalSourceAndDestinationException {
        if (fDto.getFromLocation().equals(fDto.getToLocation())){
            throw new IdenticalSourceAndDestinationException(fDto.getFromLocation(),fDto.getToLocation());
        }

        Flight f = mapper.map(fDto,Flight.class);
        String fid = UUID.randomUUID().toString();
        f.setFlightId(fid);
        LocalTime departTime = f.getDepartureTime();
        LocalTime arriveTime = f.getArrivalTime();

        if (arriveTime.isBefore(departTime)) {
            // Consider what should happen if the arrival time is before the departure time
            // For example, throw an exception or handle it according to your business logic
            throw new IllegalArgumentException("Arrival time cannot be before departure time");
        }

        Duration dur = Duration.between(departTime,arriveTime);
        f.setTimeDuration(dur);
//        int amount = f.getPricePerPerson() * f.getNoOfPassengers();
//        f.setTotalPrice(amount);
        Flight f1 = flightRepo.save(f);
        return mapper.map(f1, FlightDto.class);
    }


    @Override
    public List<FlightDto> getAllFlightDtos(String field, int offset, int pageSize){
        //Whenever we want to sort we can use Sort class and sort.direction along with on which field we want sorting to be applied on
        PageRequest pgRqst = PageRequest.of(offset,pageSize);
        Page<Flight> pagingFlight = flightRepo.findAll(pgRqst.withSort(Sort.by(Sort.Direction.ASC, field)) );
        List<Flight> flights = pagingFlight.getContent();
        return flights.stream().
                map((flight)-> mapper.map(flight,FlightDto.class)).
                collect(Collectors.toList());
    }

    public List<FlightDto> getSearchedFlights(String source, String destination, LocalDate departDate, LocalDate arrivalDat){
        List<Flight> flights = flightRepo.findByFromLocationAndToLocationAndDepartureDateAndArrivalDate(source,destination,
                departDate,arrivalDat);

        return flights.stream().
                map((flight)-> mapper.map(flight,FlightDto.class)).
                collect(Collectors.toList());
    }

    @Override
    public FlightDto getSingleFlight(String flightId){
        Flight res = flightRepo.findById(flightId).orElseThrow(()-> new ResourceNotFoundException("Flight with " + flightId + " not found"));
        return mapper.map(res,FlightDto.class);
    }

    @Override
    public FlightDto updateFlightInfo(String flightId, int nofOfSeats){
        Flight flight = flightRepo.findById(flightId).orElseThrow(()-> new ResourceNotFoundException("Flight with " + flightId + " not found"));
        flight.setAvailableSeats(nofOfSeats);
        Flight savedFlight = flightRepo.save(flight);
        return mapper.map(savedFlight,FlightDto.class);

    }



}
