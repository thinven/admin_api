package com.thinven.boot.domain.entity.employeeset.employeeauth.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.employeeset.employeeauth.EmployeeAuth;
import com.thinven.boot.domain.entity.employeeset.employeeauth.repo.EmployeeAuthRepository;
import com.thinven.boot.support.domain.entity.dao.EntityDao;
import com.thinven.boot.support.security.SHA512;

@Component("employeeauthDao")
public class EmployeeAuthDaoImpl extends EntityDao<EmployeeAuth> implements EmployeeAuthDao {

	@Autowired
	private EmployeeAuthRepository employeeAuthRepository;

	@Override
	public List<EmployeeAuth> list(EmployeeAuth entity) {
		return this.employeeAuthRepository.findAll();
	}

	@Override
	public EmployeeAuth infoByRk(String rk) {
		return this.employeeAuthRepository.findByRk(rk);
	}

	@Override
	public EmployeeAuth add(EmployeeAuth employeeAuth) {
		employeeAuth.init();
		employeeAuth.setPw(SHA512.getDigest(employeeAuth.getId()));
		employeeAuth.setRk("");
		employeeAuth.setPk("");
		return this.employeeAuthRepository.save(employeeAuth);
	}

}
