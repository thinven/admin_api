package com.thinven.boot.domain.entity.employeeset.employeeauth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employeeauth.EmployeeAuth;

public interface EmployeeAuthRepository extends JpaRepository<EmployeeAuth, String>, JpaSpecificationExecutor<EmployeeAuth>, EmployeeAuthRepositoryA {

	EmployeeAuth findByRk(String rk);

	EmployeeAuth findByEmployee(Employee employee);

}