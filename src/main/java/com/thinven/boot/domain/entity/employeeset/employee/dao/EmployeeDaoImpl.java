package com.thinven.boot.domain.entity.employeeset.employee.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employee.repo.EmployeeRepository;
import com.thinven.boot.support.domain.entity.dao.EntityDao;

@Component("employeeDao")
public class EmployeeDaoImpl extends EntityDao<Employee> implements EmployeeDao {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> list(Employee entity) {
		return this.employeeRepository.list(entity);
	}

	@Override
	public Long count(Employee entity) {
		return this.employeeRepository.count(entity);
	}

	@Override
	public Employee info(Employee entity) {
		return this.employeeRepository.findById(entity.getUid()).orElse(null);
	}

	@Override
	public Employee add(Employee entity) {
		entity.init();
		entity.setDelete(20L);
		return this.employeeRepository.save(entity);
	}

}
