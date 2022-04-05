package com.home.spring.registration_xml.utility;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.home.spring.registration_xml.dao.StudentDAOO;
import com.home.spring.registration_xml.entity.Student;
import com.home.spring.registration_xml.entity.User;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	StudentDAOO studentDAO;
	
	@Autowired
	PasswordEncoder bOryptEncoder;
	
	public CustomAuthenticationProvider() {
		System.out.println("Custom Authentication");
	}
	
	@Override
	public org.springframework.security.core.Authentication authenticate(
			org.springframework.security.core.Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		System.out.println(authentication.getName());
		String password = authentication.getCredentials().toString();
		System.out.println(authentication.getCredentials().toString());
		
		Student s = studentDAO.getStudent(authentication.getName());

		if(!bOryptEncoder.matches(password, s.getPassword())) {
			throw new BadCredentialsException("Incorrect Cred");
		}
		return new UsernamePasswordAuthenticationToken(authentication.getName(),password, new ArrayList<>());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
