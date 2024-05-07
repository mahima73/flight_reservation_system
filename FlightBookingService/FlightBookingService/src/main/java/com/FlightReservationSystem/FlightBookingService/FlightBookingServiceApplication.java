package com.FlightReservationSystem.FlightBookingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class FlightBookingServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(FlightBookingServiceApplication.class, args);
	}

}
