package com.thinven.boot.domain.entity.employeeset.employeeauth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employeeauth.EmployeeAuth;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.InfoService;
import com.thinven.boot.support.domain.entity.service.MemberInfoService;

public interface EmployeeAuthService extends MemberInfoService, UserDetailsService, InfoService<EmployeeAuth> {

	public static final long YES = 1;
	public static final long NO = 0;

	public Message<Employee> add(Message<Employee> msg, Employee info);

	public Message<Employee> update(Message<Employee> msg, Employee employee);

}
