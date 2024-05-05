package com.FlightReservationSystem.FlightBookingService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    private String bookingId;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private LocalDate travelDate;
    private List<String> passengers;
    private int numOfSeatsBooked;
    private String status;
    private int amount;
    private String modeOfPayment;
    private int userId;
    private String flightId;
    private boolean isReminderAlertSent;

}
