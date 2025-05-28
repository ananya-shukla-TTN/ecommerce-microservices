package com.ttn.consumer.validation.annotation;

import com.ttn.consumer.validation.validator.IndianContactValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IndianContactValidator.class)
public @interface IndianNumberMatch {
    String message() default "Invalid indian phone number, must start with +91";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
