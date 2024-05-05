package com.flightbookingSystem.userService.controllers;

import com.flightbookingSystem.userService.dao.UserDto;
import com.flightbookingSystem.userService.entities.FlightDto;
import com.flightbookingSystem.userService.entities.User;
import com.flightbookingSystem.userService.exceptions.ResourceNotFoundException;
import com.flightbookingSystem.userService.externalService.FlightService;
import com.flightbookingSystem.userService.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userSrvc;

    @Autowired
    private FlightService flightService;

//    @Autowired
//    private Logger logger = LoggerFactory.getLogger(UserController.class);



    @PostMapping()
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
//        logger.info("correct");
        UserDto dto = userSrvc.addUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{usernam}")
    public ResponseEntity<UserDto> updateUserWithUsername(@PathVariable(name = "usernam") String username, @RequestBody UserDto uDto) throws ResourceNotFoundException{
        UserDto u = userSrvc.updateUser(username,uDto);
        return ResponseEntity.status(HttpStatus.FOUND).body(u);
    }

    @GetMapping("/flightssearch")
    public ResponseEntity<List<FlightDto>> searchFlights(@RequestParam String source, @RequestParam String destination,
                               @RequestParam LocalDate departDate, @RequestParam LocalDate arrivDate) throws ResourceNotFoundException {
        System.out.println("Reqst came here");
        List<FlightDto> flightDtos = flightService.searchFlightsWithToFromLocation(source,destination,departDate,arrivDate);
        if(flightDtos.isEmpty()){
            throw new ResourceNotFoundException();
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(flightDtos);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(flightDtos);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable(name = "userId") int usrId) throws ResourceNotFoundException {
        UserDto userDtoFound = userSrvc.fetchSingleUser(usrId);
        return ResponseEntity.status(HttpStatus.OK).body(userDtoFound);
    }










//    //this is how we call a third party API
//    @GetMapping("/thirdpartyapi")
//    public String testFreeApi(){
//        System.out.println("third party api");
//        String uri = "https://api.agify.io/?name=muneer";
////        String uri = "https://jsonplaceholder.typicode.com/posts";
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri,String.class);
//        return result;
//    }





}
