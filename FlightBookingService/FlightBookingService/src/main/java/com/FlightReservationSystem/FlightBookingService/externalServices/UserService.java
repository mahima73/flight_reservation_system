package com.FlightReservationSystem.FlightBookingService.externalServices;

import com.FlightReservationSystem.FlightBookingService.exceptions.ResourceNotFoundException;
import com.FlightReservationSystem.FlightBookingService.payload.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="USERSERVICE")
public interface UserService {

    @GetMapping("/users/{userId}")
    UserDto fetchSingleUser(@PathVariable int userId) throws ResourceNotFoundException;
}
