package com.home.spring.restapi.security;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthentication implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("admin");
		
		
		List<GrantedAuthority> role = new ArrayList<GrantedAuthority>();
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		System.out.println(username+"  "+password);
		if(list.contains(username)&&list.contains(password)){
			role.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return new UsernamePasswordAuthenticationToken(username, password, role);
		}
		role.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new UsernamePasswordAuthenticationToken(username, password, role);
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

	
}
