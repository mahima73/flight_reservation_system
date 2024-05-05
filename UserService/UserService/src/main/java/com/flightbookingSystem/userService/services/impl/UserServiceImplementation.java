package com.flightbookingSystem.userService.services.impl;

import com.flightbookingSystem.userService.dao.UserDto;
import com.flightbookingSystem.userService.entities.User;
import com.flightbookingSystem.userService.exceptions.ResourceNotFoundException;
import com.flightbookingSystem.userService.repositories.UserRepository;
import com.flightbookingSystem.userService.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository usrRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto addUser(UserDto userDto) {
        System.out.println("added user");
        User u = this.mapper.map(userDto, User.class);
        User savedUsr = this.usrRepo.save(u);
        return mapper.map(savedUsr, UserDto.class);

    }

    @Override
    public UserDto updateUser(String username, UserDto uDto) throws ResourceNotFoundException {
        User w_usr = usrRepo.findByUserName(username);
        if(w_usr == null){
            throw new ResourceNotFoundException(username + " not found");
        }

        w_usr.setUserName(uDto.getUserName());
        w_usr.setName(uDto.getName());
        w_usr.setMobNo(uDto.getMobNo());
        w_usr.setEmail(uDto.getEmail());
        User saved = usrRepo.save(w_usr);

        return mapper.map(saved,UserDto.class);
    }

    @Override
    public User viewUserDetails() {
        return null;
    }

    @Override
    public UserDto fetchSingleUser(int userid) throws ResourceNotFoundException {
        User usr = usrRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("user with " + userid + " not found"));
        return mapper.map(usr,UserDto.class);
    }
}
