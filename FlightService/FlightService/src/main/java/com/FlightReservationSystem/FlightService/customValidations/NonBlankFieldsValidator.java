package com.FlightReservationSystem.FlightService.customValidations;

import com.FlightReservationSystem.FlightService.customValidations.annotations.NotBlankFields;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class NonBlankFieldsValidator implements ConstraintValidator<NotBlankFields,Object> {

    Logger logger = LoggerFactory.getLogger(NonBlankFieldsValidator.class);
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        logger.info("blank fields");
        if(value == null){
            return false;
        }

        Field[] fields = value.getClass().getDeclaredFields();
        for(Field fld: fields){
            fld.setAccessible(true);
            try {
                Object o = fld.get(value);
                if(o instanceof String && ((String) o).isBlank()){
                    logger.info("blank field encountered");
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("Empty field not allowed")
                            .addPropertyNode(fld.getName())
                            .addConstraintViolation();
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        //this code will not work because its converting field itself to string and checking if field is blanks its not checking value

//        for(Field f: flds){
//            try{
//                if (f.toString().isBlank()){
//                    return false;
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }
        return true;
    }
}
