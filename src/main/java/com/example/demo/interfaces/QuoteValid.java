package com.example.demo.interfaces;

import com.example.demo.validator.QuoteTypeValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = QuoteTypeValidator.class)
@Target({ ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QuoteValid {
	String message();
}
