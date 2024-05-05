package com.FlightReservationSystem.FlightService.customValidations;

import com.FlightReservationSystem.FlightService.customValidations.annotations.InvalidTravelClass;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InvalidTravelClassValidator implements ConstraintValidator<InvalidTravelClass,String> {

    Logger logger = LoggerFactory.getLogger(InvalidTravelClassValidator.class);
    @Override
    public boolean isValid(String travelClass, ConstraintValidatorContext context) {
        List<String> travelClasses = List.of("economy","premium","business");
        return travelClasses.contains(travelClass.toLowerCase());
    }
}
