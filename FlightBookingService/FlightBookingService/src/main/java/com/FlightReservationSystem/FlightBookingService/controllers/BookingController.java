package com.FlightReservationSystem.FlightBookingService.controllers;

import com.FlightReservationSystem.FlightBookingService.entities.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.FlightReservationSystem.FlightBookingService.services.BookingService;

import java.awt.print.Book;
import java.util.List;


@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @PostMapping
    public ResponseEntity<Booking> bookFlightTicket(@RequestBody Booking booking){
        Booking res = bookingService.bookFlight(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBooking(){
        List<Booking> bookingList = bookingService.getAllBookings();
        return ResponseEntity.ok(bookingList);
    }

    @GetMapping("/{bookid}")
    public ResponseEntity<Booking> fetchSingleBooking(@PathVariable String bookId){
        Booking foundBooking = bookingService.getSingleBooking(bookId);
        return ResponseEntity.ok(foundBooking);
    }
}
