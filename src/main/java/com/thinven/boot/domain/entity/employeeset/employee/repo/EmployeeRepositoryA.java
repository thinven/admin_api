package com.thinven.boot.domain.entity.employeeset.employee.repo;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.support.domain.entity.repository.CountRepository;
import com.thinven.boot.support.domain.entity.repository.ListRepository;

public interface EmployeeRepositoryA extends ListRepository<Employee>, CountRepository<Employee> {

}
