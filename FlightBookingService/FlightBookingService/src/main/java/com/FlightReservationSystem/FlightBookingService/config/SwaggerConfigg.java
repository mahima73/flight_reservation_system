package com.FlightReservationSystem.FlightBookingService.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

//This annotation works at class level and in swagger ui page we will see these configurations like title,description
// at very top
@OpenAPIDefinition(info = @Info(title = "BookingController",
        version = "API/V1/2024-05-08",
        contact = @Contact(name = "Mahima Tolani",email = "mahima@gmail.com"),
        description = "This is FlightBooking Controller having all endpoints related to booking"))
public class SwaggerConfigg{
}
