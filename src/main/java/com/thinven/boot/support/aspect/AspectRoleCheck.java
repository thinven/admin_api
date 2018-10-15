package com.thinven.boot.support.aspect;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.thinven.boot.domain.entity.employeeset.employeeauth.service.EmployeeAuthCacheService;
import com.thinven.boot.support.constant.Roles;

@Aspect
@Component
public class AspectRoleCheck {

	@Autowired
	private EmployeeAuthCacheService employeeAuthCacheService;

	@Around("execution(* *..*Controller.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object retValue = null;
		try {
			HttpServletRequest request = getRequest(joinPoint);
			String uri = request.getRequestURI();
			String rk = request.getHeader("x-auth-token");
			String roles = "";
			if (rk != null && !"".equals(rk))
				roles = this.employeeAuthCacheService.infoByRkForAop(rk);

			Map<String, String> rolesMap = getRoles();
			for (String uriPattern : rolesMap.keySet()) {
				if (uri.matches(uriPattern)) {
					String rolePattern = rolesMap.get(uriPattern);
					if (roles.matches(rolePattern)) {
						retValue = joinPoint.proceed();
						break;
					} else {
						break;// uri매칭이 참인 경우는 다른 uri못타게 하기.
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retValue;
	}

	private Map<String, String> getRoles() {
		Map<String, String> roles = new HashMap<String, String>();
		roles.put("/employees", "(.*)" + Roles.SYSTEM_ADMIN + "(.*)");
		roles.put("/commonCode(.*)", "(.*)" + Roles.SYSTEM_ADMIN + "(.*)");
		roles.put("(.*)", "(.*)");
		return roles;
	}

	private HttpServletRequest getRequest(ProceedingJoinPoint joinPoint) {
		for (Object obj : joinPoint.getArgs()) {
			if (obj instanceof HttpServletRequest || obj instanceof MultipartHttpServletRequest) {
				return (HttpServletRequest) obj;
			}
		}
		return null;
	}

}
