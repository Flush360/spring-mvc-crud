package springdemo.apect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Pointcut("execution(* springdemo.controller.*.*(..))")
	public void forController() {
	}

	@Pointcut("execution(* springdemo.service.*.*(..))")
	public void forService() {
	}

	@Pointcut("execution(* springdemo.dao.*.*(..))")
	public void forDAO() {
	}

	@Pointcut("forController() || forService() || forDAO()")
	private void forAppFlow() {
	}

	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();
		logger.info("\n=>> in @before " + method);
		Object[] args = joinPoint.getArgs();
		for (Object temp : args) {
			logger.info("=> argument " + temp);
		}
	}

	@AfterReturning(pointcut = "forAppFlow()", returning = "res")
	public void after(JoinPoint joinPoint, Object res) {

		String method = joinPoint.getSignature().toShortString();
		logger.info("\n=>> in @afterReturning " + method);

		logger.info("===> result is " + res);

	}

}
