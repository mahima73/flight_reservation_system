package com.FlightReservationSystem.FlightService.customValidations.annotations;

import com.FlightReservationSystem.FlightService.customValidations.InvalidTravelClassValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InvalidTravelClassValidator.class)
public @interface InvalidTravelClass {
    String message() default "Invalid Travel Class. Please enter only business/economy/premium ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
