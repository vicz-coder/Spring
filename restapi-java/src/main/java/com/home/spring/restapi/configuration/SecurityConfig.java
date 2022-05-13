package com.home.spring.restapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.home.spring.restapi.security.CustomAuthentication;
import com.home.spring.restapi.security.CustomDenied;
import com.home.spring.restapi.security.CustomEntryPoint;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean 
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomDenied();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
		return new CustomAuthentication();
	}
	
	@Bean
	public AuthenticationEntryPoint customEntryPoint() {
		return new CustomEntryPoint();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
	auth.authenticationProvider(authProvider());
	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/api/students").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/students/**").hasRole("ADMIN")
		.and()
		.httpBasic()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
	
		.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
		.authenticationEntryPoint(customEntryPoint());

	}

}
