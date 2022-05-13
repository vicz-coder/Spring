package com.home.spring.restapi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.spring.restapi.entity.ErrorResponse;
import com.home.spring.restapi.exception.AccessException;

@Component
public class CustomDenied implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws AccessException, IOException, ServletException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user = "";
		if(auth!=null)
		{
			user = auth.getName();
		}
		response.setStatus(403);
		List<String> details = new ArrayList<String>();
		details.add("Access Denied for User :"+user);
		ErrorResponse error = new ErrorResponse("INNCORRECT REQUEST", details);
		String json = mapper.writeValueAsString(error);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
		
	}

}
