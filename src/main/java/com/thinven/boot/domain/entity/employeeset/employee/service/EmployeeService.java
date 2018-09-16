package com.thinven.boot.domain.entity.employeeset.employee.service;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.support.domain.entity.service.AddService;
import com.thinven.boot.support.domain.entity.service.ListService;

public interface EmployeeService extends ListService<Employee>, AddService<Employee> {

	public static final long YES = 1;
	public static final long NO = 0;

}
