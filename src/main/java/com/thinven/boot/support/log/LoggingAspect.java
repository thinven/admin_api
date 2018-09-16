package com.thinven.boot.support.log;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class LoggingAspect {

	private Log logger = LogFactory.getLog(getClass());

	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {

		String strClassName = joinPoint.getThis().getClass().getSimpleName().substring(0, joinPoint.getThis().getClass().getSimpleName().indexOf("$$"));
		String sessionid = "";
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		HttpServletRequest request = null;
		for (Object obj : joinPoint.getArgs()) {
			if (obj instanceof HttpServletRequest) {
				request = (HttpServletRequest) obj;
				break;
			}
		}
		if (request != null) {
			sessionid = request.getSession().getId();
		}

		logger.info(" \n-------------------------------------------> Start " + joinPoint.getSignature().getName() + "() in " + strClassName + " {" + sessionid
				+ "}");

		if (request != null) {
			com.thinven.boot.support.log.Log.showParameter(joinPoint.getThis(), request);
		}

		Object retValue = null;
		try {
			retValue = joinPoint.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}

		stopWatch.stop();
		logger.info(
				" > End " + joinPoint.getSignature().getName() + "() in " + strClassName + " {" + stopWatch.getTotalTimeMillis() + "ms}.{" + sessionid + "}");
		return retValue;
	}

}