package com.FlightReservationSystem.FlightBookingService.services.impl;
import com.FlightReservationSystem.FlightBookingService.entities.Booking;
import com.FlightReservationSystem.FlightBookingService.exceptions.ResourceNotFoundException;
import com.FlightReservationSystem.FlightBookingService.exceptions.SeatsNotAvailableException;
import com.FlightReservationSystem.FlightBookingService.externalServices.FlightService;
import com.FlightReservationSystem.FlightBookingService.externalServices.UserService;
import com.FlightReservationSystem.FlightBookingService.payload.FlightDto;
import com.FlightReservationSystem.FlightBookingService.payload.UserDto;
import com.FlightReservationSystem.FlightBookingService.repositories.BookingRepository;
import com.FlightReservationSystem.FlightBookingService.services.BookingService;
import com.FlightReservationSystem.FlightBookingService.services.EmailService;
import org.apache.catalina.User;
import org.bouncycastle.pqc.crypto.newhope.NHSecretKeyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationServiceNotRegisteredException;
import java.awt.print.Book;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService{
    private final BookingRepository flightBookingRepo;
    private final FlightService flightService;
    private final UserService usrSrvc;
    private final EmailService mailService;

    Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
    public BookingServiceImpl(BookingRepository bookingRepo, FlightService flightService,UserService userService,
                              EmailService emailService){
        this.mailService = emailService;
        this.flightBookingRepo = bookingRepo;
        this.flightService = flightService;
        this.usrSrvc = userService;
    }
    @Override
    public Booking bookFlight(Booking booking) {
        validateBooking(booking);

        FlightDto flightDto = flightService.getSingleFlight(booking.getFlightId());
        UserDto userDto = usrSrvc.fetchSingleUser(booking.getUserId());

        checkAvailability(flightDto, booking.getNumOfSeatsBooked());

        String bookId = UUID.randomUUID().toString();
        int pricePerPerson = flightDto.getPricePerPerson();
        int noOfPassengers = booking.getNumOfSeatsBooked();
        int totalAvailableSeats = flightDto.getAvailableSeats();
        int seatsLeft = totalAvailableSeats - noOfPassengers;

        //Asia/Kolkata Zone gives us the actual time and date
        booking.setBookingDate(LocalDate.now(ZoneId.of("Asia/Kolkata")));
        booking.setBookingTime(LocalTime.now(ZoneId.of("Asia/Kolkata")));
        booking.setBookingId(bookId);
        booking.setTravelDate(flightDto.getDepartureDate());
        booking.setAmount(pricePerPerson * noOfPassengers);
        Booking savedBooking = flightBookingRepo.save(booking);

        //update no of available seats after successful booking
        flightService.updateFlightInfo(booking.getFlightId(),seatsLeft);

        //send confirmation email
        sendConfirmationMail(flightDto,userDto,savedBooking,userDto.getEmail());
        return savedBooking;
    }

    private void validateBooking(Booking bookingDetails){
        if (bookingDetails.getFlightId() == null || bookingDetails.getUserId() == 0){
            throw new ResourceNotFoundException("Invalid booking Details provided");
        }
    }

    private void checkAvailability(FlightDto fDto, int seatsBooked){
        if(fDto.getAvailableSeats() < seatsBooked){
            throw new SeatsNotAvailableException("Sorry in this Flight " + seatsBooked +" seats not available" );
        }
    }

    private void sendConfirmationMail(FlightDto flightDto, UserDto userDto,Booking savedBooking,String toMail){
        String message = String.format(
                "Hello %s, Your ticket for flight %s is successfully booked. Please find details below: \nFrom Place: %s" +
                        "\nTo Place: %s\n Number of seats booked: %d\nPassengers are: %s\n",
                userDto.getName(),
                flightDto.getFlightName(),
                flightDto.getFromLocation(),
                flightDto.getToLocation(),
                savedBooking.getNumOfSeatsBooked(),
                savedBooking.getPassengers()
        );

        mailService.sendEmail(toMail,"Ticket confirmed",message);
    }

    private void sendReminderEmail(String name, String toEmail){
        String message = String.format("Dear %s, This is a reminder email about your flight that is going to departure soon in 1 hour. " +
                "Please prepare yourself\n" +
                "Happy Journey!!",
                name);
        mailService.sendEmail(toEmail,"Reminder email for Flight soon to departure",message);
    }

//    @Scheduled(fixedRate = 60000L)
//    public void checkAndAlertMail(){
//        logger.info("alert mail bhjne ki koshish");
//        List<Booking> bookings = getAllBookings();
//        //Looping through all bookings and fetching flight time
//        for(Booking bookingElem: bookings){
//            FlightDto flightDto = flightService.getSingleFlight(bookingElem.getFlightId());
//            LocalTime flightDepartureTime = flightDto.getDepartureTime();
//            LocalTime currentSystemTime = LocalTime.now(ZoneId.of("Asia/Kolkata"));
//            boolean isAlertSent = bookingElem.isReminderAlertSent();
//            if((ChronoUnit.HOURS.between(currentSystemTime,flightDepartureTime) == 1) && !isAlertSent){
//                System.out.println("email sent");
//                isAlertSent = true;
//                bookingElem.setReminderAlertSent(isAlertSent);
//                flightBookingRepo.save(bookingElem);
//                UserDto usrDto = usrSrvc.fetchSingleUser(bookingElem.getUserId());
//                String fullName = usrDto.getName();
//                sendReminderEmail(fullName,usrDto.getEmail());

//            }

//        }
//    }

    @Override
    public List<Booking> getAllBookings(){
        return flightBookingRepo.findAll();
    }

    @Override
    public Booking getSingleBooking(String bookingId) {
        return flightBookingRepo.findById(bookingId).orElseThrow(()-> new ResourceNotFoundException("Sorry this booking id " + bookingId + " doesn't exist"));
    }
}
