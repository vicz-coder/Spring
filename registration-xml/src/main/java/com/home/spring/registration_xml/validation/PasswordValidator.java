package com.home.spring.registration_xml.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.home.spring.registration_xml.entity.Student;

public class PasswordValidator implements ConstraintValidator<PasswordAnno, Student>{

	@Override
	public void initialize(PasswordAnno constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Student stud, ConstraintValidatorContext context) {
		if(stud.getPassword().equals(stud.getConfirmpassword()))
			return true;
		else
		return false;
	}

	
	
	
}
