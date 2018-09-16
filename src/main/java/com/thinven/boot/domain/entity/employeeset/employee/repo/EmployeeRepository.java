package com.thinven.boot.domain.entity.employeeset.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>, JpaSpecificationExecutor<Employee>, EmployeeRepositoryA {

}