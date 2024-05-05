package com.FlightReservationSystem.FlightService.repositories;

import com.FlightReservationSystem.FlightService.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,String> {
//    @Query("select f from Flight f where " +
//            "LOWER(f.fromLocation) like LOWER(CONCAT('%',:source,'%')) and " +
//            "LOWER(f.toLocation)   like LOWER(CONCAT('%',:destination,'%'))")
//    List<Flight> findByFromLocationAndToLocation(@Param("source") String src, @Param("destination") String dest);
    List<Flight> findByFromLocationAndToLocationAndDepartureDateAndArrivalDate(String src, String dest, LocalDate departDate, LocalDate arrivDate);

    List<Flight> findByFromLocation(String src);
}
