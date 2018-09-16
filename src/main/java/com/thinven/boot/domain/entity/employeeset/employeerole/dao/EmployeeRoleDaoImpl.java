package com.thinven.boot.domain.entity.employeeset.employeerole.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.employeeset.employeerole.EmployeeRole;
import com.thinven.boot.domain.entity.employeeset.employeerole.repo.EmployeeRoleRepository;
import com.thinven.boot.support.domain.entity.dao.EntityDao;

@Component("employeeroleDao")
public class EmployeeRoleDaoImpl extends EntityDao<EmployeeRole> implements EmployeeRoleDao {

	@Autowired
	private EmployeeRoleRepository employeeroleRepository;

	@Override
	public List<EmployeeRole> list(EmployeeRole entity) {
		return this.employeeroleRepository.findAll();
	}

}
