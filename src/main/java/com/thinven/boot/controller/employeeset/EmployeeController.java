package com.thinven.boot.controller.employeeset;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employee.service.EmployeeService;
import com.thinven.boot.domain.wrapper.WrapperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/employees")
@Api(tags = "직원")
public class EmployeeController {

	@Autowired
	private WrapperService<Employee> wrapperService;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "직원 목록 조회", notes = "사이트를 사용하는 직원 목록을 조회하는 API.")
	public ModelAndView list(String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		Employee employee = new Employee(p1, p2, p3);
		return this.wrapperService.list(this.employeeService, employee, request).toMAV();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "직원 등록", notes = "사이트를 사용하는 직원을 등록하는 API.")
	public ModelAndView add(String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		Employee employee = new Employee(p1, p2, p3);
		return this.wrapperService.add(this.employeeService, employee, request).toMAV();
	}

	@RequestMapping(method = RequestMethod.PATCH)
	@ApiOperation(value = "직원 수정", notes = "사이트를 사용하는 직원정보를 수정하는 API.")
	public ModelAndView update(@RequestBody Map<String, Object> payload, HttpServletRequest request) throws IOException {
		Employee employee = new Employee(payload);
		return this.wrapperService.update(this.employeeService, employee, request).toMAV();
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@ApiOperation(value = "직원 삭제", notes = "사이트를 사용하는 직원정보를 삭제하는 API.")
	public ModelAndView delete(@RequestBody Map<String, Object> payload, HttpServletRequest request) throws IOException {
		Employee employee = new Employee(payload);
		return this.wrapperService.delete(this.employeeService, employee, request).toMAV();
	}

}
