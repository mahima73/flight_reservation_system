package com.flightbookingSystem.userService.dao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@NoArgsConstructor

public class UserDto {
    @NotBlank
    @Size(min = 4, max = 20, message = "Full name of the User should be atleast 4 characters")
    private String name;

    @Email(message = "Invalid email passed")
    private String email;

    @Size(min = 10, message = "Incorrect length of mobile number. It should be of 10 digits")
    private String mobNo;

    @Size(min = 3, max = 5)
    @NotBlank
    private String userName;

    @NotBlank
    @Size(min = 6, max = 10)
    private String password;
}
