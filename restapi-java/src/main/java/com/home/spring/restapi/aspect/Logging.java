package com.home.spring.restapi.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {

	Logger logger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution (* com.home.spring.restapi.*.*.*(..))")
	public void startPoint() {};
	
	@Pointcut("execution (* com.home.spring.restapi.aspect.*.*(..))")
	public void logAspect() {};
	
	@Before("startPoint() && !logAspect()")
	public void logStart(JoinPoint jp) {
		logger.info("Method Invocation :"+jp.getSignature());
	}
	
	
	@AfterReturning(pointcut = "startPoint() && !logAspect()",returning = "result")
	public Object logEnd(JoinPoint jp,Object result) {
		
		logger.info("Method Invocation Ended for :"+jp.getSignature());
		return result;
	}
	
	@AfterThrowing(pointcut = "startPoint() && !logAspect()",throwing = "exec")
	public void logexception(JoinPoint jp,Throwable exec) {
		logger.warning("Execption at "+jp.getSignature()+" with "+exec.getLocalizedMessage());
	}
	
	
	
}
