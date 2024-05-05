package com.FlightReservationSystem.FlightBookingService.repositories;

import com.FlightReservationSystem.FlightBookingService.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,String> {

}
