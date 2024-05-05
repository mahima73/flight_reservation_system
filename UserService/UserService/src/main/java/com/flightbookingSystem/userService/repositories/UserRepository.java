package com.flightbookingSystem.userService.repositories;

import com.flightbookingSystem.userService.dao.UserDto;
import com.flightbookingSystem.userService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
