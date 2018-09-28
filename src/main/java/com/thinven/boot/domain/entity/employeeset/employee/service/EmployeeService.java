package com.thinven.boot.domain.entity.employeeset.employee.service;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.support.domain.entity.service.AddService;
import com.thinven.boot.support.domain.entity.service.DeleteService;
import com.thinven.boot.support.domain.entity.service.InfoService;
import com.thinven.boot.support.domain.entity.service.ListService;
import com.thinven.boot.support.domain.entity.service.UpdateService;

public interface EmployeeService extends ListService<Employee>, InfoService<Employee>, AddService<Employee>, UpdateService<Employee>, DeleteService<Employee> {

}
