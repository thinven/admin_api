package com.thinven.boot.support.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class AspectLogging {

	private static final Logger logger = LoggerFactory.getLogger(AspectLogging.class);

	@Around("execution(* *..*Controller.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		String strClassName = joinPoint.getThis().getClass().getSimpleName().substring(0, joinPoint.getThis().getClass().getSimpleName().indexOf("$$"));
		logger.info("▷▶▷▶ " + joinPoint.getSignature().getName() + "() in " + strClassName);

		Object retValue = null;
		try {
			retValue = joinPoint.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}

		stopWatch.stop();
		logger.info("▶▶▶▶ ꒰◍ˊ◡ˋ꒱ " + joinPoint.getSignature().getName() + "() in " + strClassName + " {" + stopWatch.getTotalTimeMillis() + "ms}");
		return retValue;
	}

	@Before("execution(* *..*Controller.*(..))")
	public void onBeforeHandler(JoinPoint joinPoint) {

	}

	@After("execution(* *..*Controller.*(..))")
	public void onAfterHandler(JoinPoint joinPoint) {

	}

	@AfterReturning(pointcut = "execution(* com.example.service.*.*Aop(..))", returning = "str")
	public void onAfterReturningHandler(JoinPoint joinPoint, Object str) {
		logger.info("@AfterReturning : " + str);
		logger.info("=============== onAfterReturningHandler");
	}

	@Pointcut("execution(* com.example.service.*.*Aop(..))")
	public void onPointcut(JoinPoint joinPoint) {
		logger.info("=============== onPointcut");
	}
}
