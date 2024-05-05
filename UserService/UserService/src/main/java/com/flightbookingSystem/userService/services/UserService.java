package com.flightbookingSystem.userService.services;

import com.flightbookingSystem.userService.dao.UserDto;
import com.flightbookingSystem.userService.entities.User;
import com.flightbookingSystem.userService.exceptions.ResourceNotFoundException;
import jakarta.annotation.Resource;

import java.lang.module.ResolutionException;

public interface UserService {
    //book flight
    //search flights
    //sort flight based on filter like price
    //update information like
    //create
    //update
    //view
    //signoff

    //user can register its details on website
    UserDto addUser(UserDto userdtoo);

    //user can update
    UserDto updateUser(String username, UserDto uDto) throws ResourceNotFoundException;

    //user can view details also he can view past bookings
    User viewUserDetails();

    UserDto fetchSingleUser(int userid) throws ResourceNotFoundException;

}
