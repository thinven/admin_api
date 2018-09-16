package com.thinven.boot.domain.entity.employeeset.employee.dao;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.support.domain.entity.dao.AddDao;
import com.thinven.boot.support.domain.entity.dao.CountDao;
import com.thinven.boot.support.domain.entity.dao.ListDao;

public interface EmployeeDao extends ListDao<Employee>, CountDao<Employee>, AddDao<Employee> {

}
