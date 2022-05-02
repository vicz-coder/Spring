package com.home.spring.aspect;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	Logger logger = Logger.getLogger(getClass().getName());

	@Pointcut("execution (* com.home.spring.dao.*.*(..))")
	public void dbAction() {}
	
	
	@Before("execution(* com.home.spring.controller.*.*(..))")
	public void logController(JoinPoint jp) {
		logger.info("Controller Invocation "+jp.getSignature());
	}
	
	
	  @Around("dbAction()") public Object aroundLog(ProceedingJoinPoint jp) throws
	  Throwable{ logger.info("Starting DB Action "+jp.getSignature()); try { Object
	  result = jp.proceed(); return result; } catch (Exception e) { throw e; } }
	 
	
}
