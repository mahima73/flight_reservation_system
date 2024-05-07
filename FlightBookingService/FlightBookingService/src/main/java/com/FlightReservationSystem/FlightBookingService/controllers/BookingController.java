package com.FlightReservationSystem.FlightBookingService.controllers;

import com.FlightReservationSystem.FlightBookingService.entities.Booking;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
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
    @Hidden
    //hidden annotation hides the REST API from swagger html page where all API will be shown. If we want that Delete API should be hidden we can use this annotation
    public ResponseEntity<Booking> bookFlightTicket(@RequestBody Booking booking){
        Booking res = bookingService.bookFlight(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping
    //By using the Operation annotation we can specify for a particular API about its description and tags
    @Operation(tags = "GetAllBookingsTag", description = "this API will get us all the bookings done by user")
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
