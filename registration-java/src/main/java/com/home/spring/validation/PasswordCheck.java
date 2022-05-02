package com.home.spring.validation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.springframework.validation.annotation.Validated;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
@Constraint(validatedBy = PasswordContraint.class)
public @interface PasswordCheck {
	public String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
