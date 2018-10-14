package com.thinven.boot.controller.employeeset;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.thinven.boot.domain.entity.employeeset.employeeauth.EmployeeAuth;
import com.thinven.boot.domain.entity.employeeset.employeeauth.service.EmployeeAuthService;
import com.thinven.boot.domain.wrapper.WrapperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/employeeAuth")
@Api(tags = "직원인증")
public class EmployeeAuthController {

	@Autowired
	private WrapperService<EmployeeAuth> wrapperService;

	@Autowired
	private EmployeeAuthService employeeAuthService;

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "직원인증 얻기", notes = "사이트 접속 인증 얻기 API.")
	public ModelAndView info(String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		EmployeeAuth employeeAuth = new EmployeeAuth(p1, p2, p3);
		return this.wrapperService.info(this.employeeAuthService, employeeAuth, request).toMAV();
	}

}
