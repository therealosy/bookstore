package com.bookstore.util.validator;

import com.bookstore.util.constraint.ISBNConstraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ISBNValidator implements ConstraintValidator<ISBNConstraint, String> {

    @Override
    public void initialize(ISBNConstraint isbn) {
    }

    @Override
    public boolean isValid(String isbnField,
                           ConstraintValidatorContext context) {
        final String isbnRegex = "([0-9]+(-[0-9]+)+)";
        final Pattern pattern = Pattern.compile(isbnRegex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(isbnField);
        return matcher.matches();
    }
}
