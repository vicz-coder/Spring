package com.home.spring.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.home.spring.dao.IStudentDAO;
import com.home.spring.dao.StudentDAOImpl;
import com.home.spring.entity.Student;

@Component
public class CustomAuthentication implements AuthenticationProvider {
	
	@Autowired
	IStudentDAO studentDAO;
	
	@Autowired
	PasswordEncoder bCryptEncoder;
	


	public CustomAuthentication() {
		super();
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		String userName = authentication.getName();
		

		
		Student student = studentDAO.getStudent(userName);


		if(student==null) {
			throw new BadCredentialsException("Invalid Credentials");
		}
		
		if(!bCryptEncoder.matches(authentication.getCredentials().toString(), student.getPassword())) {
			System.out.println("NO MATCH");
			throw new BadCredentialsException("Invalid Username or Password");
		}
		
		return new UsernamePasswordAuthenticationToken(authentication.getName(),authentication.getCredentials().toString(),new ArrayList<>());
		

	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
