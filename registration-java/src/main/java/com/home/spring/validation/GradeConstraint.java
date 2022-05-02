package com.home.spring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GradeConstraint implements ConstraintValidator<GradeCheck, String>{

	private String inputValue;
	
	@Override
	public void initialize(GradeCheck constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.endsWith("+") || value.endsWith("-"))
		return true;
		else
			return false;
	}
	
	

}
