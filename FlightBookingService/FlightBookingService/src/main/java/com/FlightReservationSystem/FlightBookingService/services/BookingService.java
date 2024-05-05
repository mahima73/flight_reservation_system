package com.FlightReservationSystem.FlightBookingService.services;

import com.FlightReservationSystem.FlightBookingService.entities.Booking;

import java.util.List;

public interface BookingService {
    //create a booking and it will contain info like which user booked and what flight booked
    Booking bookFlight(Booking booking);

    //get all bookings
    List<Booking> getAllBookings();

    Booking getSingleBooking(String bookingId);


}
