package com.thinven.boot.domain.entity.employeeset.employeeauth.dao;

import com.thinven.boot.domain.entity.employeeset.employeeauth.EmployeeAuth;
import com.thinven.boot.support.domain.entity.dao.ListDao;

public interface EmployeeAuthDao extends ListDao<EmployeeAuth> {

	EmployeeAuth infoByRk(String rk);

	EmployeeAuth add(EmployeeAuth employeeAuth);

}
