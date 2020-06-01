package com.imooc.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class TimeAspect {
	
	private static final long start = 0;

	@Around("execution(* com.imooc.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("time aspect strat");
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("arg is " + arg);
		}
		long strat = new Date().getTime();
		Object object = pjp.proceed();
		System.out.println("time aspect ��ʱ:"+ (new Date().getTime() - start));
		System.out.println("time aspect end");
		return object;
	}
	
	

}
