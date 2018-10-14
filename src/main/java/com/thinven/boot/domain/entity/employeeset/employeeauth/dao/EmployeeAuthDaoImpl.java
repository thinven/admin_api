package com.thinven.boot.domain.entity.employeeset.employeeauth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employeeauth.EmployeeAuth;
import com.thinven.boot.domain.entity.employeeset.employeeauth.repo.EmployeeAuthRepository;
import com.thinven.boot.support.domain.entity.dao.EntityDao;
import com.thinven.boot.support.security.SHA512;

@Component("employeeAuthDao")
public class EmployeeAuthDaoImpl extends EntityDao<EmployeeAuth> implements EmployeeAuthDao {

	@Autowired
	private EmployeeAuthRepository employeeAuthRepository;

	@Override
	public Long count(EmployeeAuth entity) {
		return this.employeeAuthRepository.count(entity);
	}

	@Override
	public Long countDuplicateId(EmployeeAuth entity) {
		return this.employeeAuthRepository.countDuplicateId(entity);
	}

	@Override
	public EmployeeAuth infoByIdAndPw(EmployeeAuth employeeAuth) {
		return this.employeeAuthRepository.findByIdAndPw(employeeAuth.getId(), employeeAuth.getPw());
	}

	@Override
	public EmployeeAuth infoByRk(String rk) {
		return this.employeeAuthRepository.findByRk(rk);
	}

	@Override
	public EmployeeAuth infoByEmployee(Employee employee) {
		return this.employeeAuthRepository.findByEmployee(employee);
	}

	@Override
	public EmployeeAuth add(EmployeeAuth entity) {
		entity.init();
		entity.setPw(SHA512.getDigest(entity.getId()));
		entity.setRk("");
		entity.setPk("");
		return this.employeeAuthRepository.save(entity);
	}

}
