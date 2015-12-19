package com.hanains.mysite.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GlobalLogging {
	
	@Around("execution(* *..dao.*.*(..))")  // || execution(* *..service.*.*(..)) || execution(* *..controller.*.*(..))" )
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		String taskName = pjp.getTarget().getClass()+"."+pjp.getSignature().getName();
		Log log = LogFactory.getLog( pjp.getTarget().getClass() );
	
		log.error(taskName+" : Start!");

		Object result = pjp.proceed();
		
		log.error(taskName+" : End!");

		return result;
	}
}
