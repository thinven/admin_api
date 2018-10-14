package com.thinven.boot.domain.entity.employeeset.employeeauth.dao;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employeeauth.EmployeeAuth;
import com.thinven.boot.support.domain.entity.dao.CountDao;

public interface EmployeeAuthDao extends CountDao<EmployeeAuth> {

	Long countDuplicateId(EmployeeAuth entity);

	EmployeeAuth infoByIdAndPw(EmployeeAuth employeeAuth);

	EmployeeAuth infoByRk(String rk);

	EmployeeAuth infoByEmployee(Employee employee);

	EmployeeAuth add(EmployeeAuth entity);

}
