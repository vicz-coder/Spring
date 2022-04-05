package com.home.spring.registration_xml.validation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.invoke.MethodHandles.Lookup.ClassOption;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ElementType.FIELD, METHOD,ElementType.TYPE })
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordAnno {

	
	public String message() default "Password Not Match";
	Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
