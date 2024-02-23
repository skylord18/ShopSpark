package com.shopspark.ShopSpark.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.Validator;
import org.hibernate.validator.internal.constraintvalidators.hv.NormalizedValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoSpecialCharactersValidator.class)
public @interface NoSpecialCharacters {
    String message() default "Brand Name Cannot Contain Special Characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
