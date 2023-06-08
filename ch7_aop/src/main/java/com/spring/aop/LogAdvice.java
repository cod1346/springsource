package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("log") @Aspect
public class LogAdvice {
	
	@Before(value = "execution(* com.spring.aop.Product.getInfo())")
	public void beforeLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 전 호출");
	}
	
	@After(value = "execution(* com.spring.aop.Product.getInfo())")
	public void afterLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출");
	}

	@AfterThrowing(value = "execution(* com.spring.aop.Product.getInfo())")
	public void afterThrowingLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출(예외 발생 시 실행)");
	}

	@AfterReturning(value = "execution(* com.spring.aop.Product.getInfo())")
	public void afterReturningLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출(예외 발생 없을 시 실행)");
	}

	@Around(value = "execution(* com.spring.aop.Product.getInfo())")
	public void aroundLog(ProceedingJoinPoint pjp) {
		System.out.println("[공통로그] 비즈니스 로직 수행 전 호출(어라운드)");
		try {
			pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출(어라운드)");
	}
}
