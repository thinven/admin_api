package com.thinven.boot.domain.entity.employeeset.employeeauth.dao;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employeeauth.EmployeeAuth;
import com.thinven.boot.support.domain.entity.dao.CountDao;
import com.thinven.boot.support.domain.entity.dao.ListDao;

public interface EmployeeAuthDao extends ListDao<EmployeeAuth>, CountDao<EmployeeAuth> {

	Long countDuplicateId(EmployeeAuth entity);

	EmployeeAuth infoByRk(String rk);

	EmployeeAuth infoByEmployee(Employee employee);

	EmployeeAuth add(EmployeeAuth entity);

}
