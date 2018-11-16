package com.thinven.boot.support.aspect;

import java.util.ArrayList;
import java.util.List;

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

@Aspect
@Component
public class AspectRoleCheck {

	@Autowired
	private EmployeeAuthCacheService employeeAuthCacheService;

	@Around("execution(* *..*Controller.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		HttpServletRequest request = getRequest(joinPoint);
		if (request != null) {

			result = proceedWrap(request, joinPoint);
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
		return result;
	}

	private Object proceedWrap(HttpServletRequest request, ProceedingJoinPoint joinPoint) {
		String uri = request.getRequestURI();
		String rk = request.getHeader("x-auth-token");
		String roles = "";
		if (rk != null && !"".equals(rk) && !"guest".equals(rk))
			roles = this.employeeAuthCacheService.infoByRkForAop(rk);

		List<UrlRoles> rolesList = getRoles();
		for (UrlRoles urlRoles : rolesList) {
			if (uri.matches(urlRoles.getUrl())) {
				for (String rolePattern : urlRoles.getRoles()) {
					if (roles.matches(rolePattern)) {
						try {
							return joinPoint.proceed();
						} catch (Throwable e) {
							e.printStackTrace();
						}
					}
				}
				return null;
			}
		}
		return null;
	}

	private List<UrlRoles> getRoles() {
		List<UrlRoles> roles = new ArrayList<UrlRoles>();
		roles.add(new UrlRoles("/api/employees", "(.*)" + Roles.SYSTEM_ADMIN + "(.*)"));
		roles.add(new UrlRoles("/api/commonCode(.*)", "(.*)" + Roles.SYSTEM_ADMIN + "(.*)"));
		roles.add(new UrlRoles("(.*)", "(.*)"));
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
