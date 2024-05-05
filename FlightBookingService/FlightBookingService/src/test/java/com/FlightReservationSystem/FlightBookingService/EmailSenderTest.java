package com.FlightReservationSystem.FlightBookingService;

import com.FlightReservationSystem.FlightBookingService.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;

@SpringBootTest
public class EmailSenderTest {
//    @Autowired
//    private EmailService mailService;
//
//    @Test
//    void emailSend(){
//        System.out.println("mail sending");
//        mailService.sendEmail("itsfar47@gmail.com","Hi ye hai subject","Hi spring boot se bhja gaya mail");
//    }

    @Test
    @Scheduled(fixedRate = 2000L)
    public void printTime(){
        System.out.println("Time is: " + String.valueOf(LocalTime.now()));
    }
}
