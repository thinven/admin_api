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
import org.springframework.web.servlet.ModelAndView;

import com.thinven.boot.domain.entity.employeeset.employeeauth.service.EmployeeAuthCacheService;
import com.thinven.boot.support.constant.Roles;
import com.thinven.boot.support.log.Log;

@Aspect
@Component
public class AspectRoleCheck {

	@Autowired
	private EmployeeAuthCacheService employeeAuthCacheService;

	@Around("execution(* *..*Controller.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		try {
			HttpServletRequest request = getRequest(joinPoint);
			if (request != null) {
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
							result = joinPoint.proceed();
							break;
						} else {
							Log.info(this, "role matching fail");
							Log.info(this, "rk : " + rk);
							Log.info(this, "roles : " + roles);
							Log.info(this, "uriPattern : " + uriPattern);
							Log.info(this, "rolePattern : " + rolePattern);
							break;
						}
					}
				}
				if (result == null) {
					ModelAndView mav = new ModelAndView("jsonView");
					mav.addObject("key", "WAR_MSG_NEED_ROLE");
					mav.addObject("desc", "운영자의 인증이 받으셔야 합니다.");
					result = mav;
				}
			} else {
				result = joinPoint.proceed();
				if (result.toString().indexOf("springfox.documentation.swagger.web") == -1) {
					result = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
