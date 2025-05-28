package com.ttn.consumer.validation.validator;

import com.ttn.consumer.validation.annotation.IndianNumberMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IndianContactValidator implements ConstraintValidator<IndianNumberMatch, String> {

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return phoneNumber == null || phoneNumber.matches("^\\+91[-\\s]?[6-9]\\d{9}$");
    }
}
