package com.bookstore.util.constraint;

import com.bookstore.util.validator.ISBNValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Documented
@Constraint(validatedBy = ISBNValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ISBNConstraint {
    String message() default "Invalid ISBN";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
