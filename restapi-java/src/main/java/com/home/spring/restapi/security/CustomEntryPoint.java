package com.home.spring.restapi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.spring.restapi.entity.ErrorResponse;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		response.setStatus(403);
		List<String> details = new ArrayList<String>();
		details.add("Access Denied for User");
		ErrorResponse error = new ErrorResponse("INNCORRECT REQUEST", details);
		String json = mapper.writeValueAsString(error);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);

	}

}
