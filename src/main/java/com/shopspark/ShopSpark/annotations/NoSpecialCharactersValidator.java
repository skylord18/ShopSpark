package com.shopspark.ShopSpark.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoSpecialCharactersValidator implements ConstraintValidator<NoSpecialCharacters, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Check if the value contains any special characters
        return value != null && !value.matches(".*[^a-zA-Z0-9\\s].*");
    }
}
