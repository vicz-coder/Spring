package com.home.spring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.home.spring.entity.Student;

public class PasswordContraint implements ConstraintValidator<PasswordCheck, Student>{

	@Override
	public void initialize(PasswordCheck constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Student student, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(student.getPassword()==null||student.getConfirmPassword()==null)
			return false;
		else if(student.getConfirmPassword().equals(student.getPassword())) {
			return true;
		}
		
		return false;
	}
	
	

}
