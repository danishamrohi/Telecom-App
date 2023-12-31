package com.cgi.csragent.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String VALUE = "execution(* com.cgi.admin.controller..*(..)))";
	
	@Before(value=VALUE)
	  public void beforeAllMethods(JoinPoint joinPoint) throws Throwable 
	  {
	    joinPoint.getSignature().getName();
	    logger.info("{} returned with value {}", joinPoint);
	  }
	
	@After(value=VALUE)
	  public void afterAllMethods(JoinPoint joinPoint) throws Throwable 
	  {
	    joinPoint.getSignature().getName();
	    logger.info("after execution of {}", joinPoint);
	  }
	
	@AfterReturning(value=VALUE, returning = "result")
	  public void afterReturningAllMethods(JoinPoint joinPoint, Object result) throws Throwable 
	  {
	    joinPoint.getSignature().getName();
	    logger.info("{} returned with value {}", joinPoint, result);
	  }
	
	@AfterThrowing(value=VALUE)
	  public void afterThrowingAllMethods(JoinPoint joinPoint) throws Throwable 
	  {
	    joinPoint.getSignature().getName();
	  }
}