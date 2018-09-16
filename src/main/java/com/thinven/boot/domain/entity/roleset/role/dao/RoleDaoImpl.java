package com.thinven.boot.domain.entity.roleset.role.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.roleset.role.Role;
import com.thinven.boot.domain.entity.roleset.role.repo.RoleRepository;
import com.thinven.boot.support.domain.entity.dao.EntityDao;

@Component("roleDao")
public class RoleDaoImpl extends EntityDao<Role> implements RoleDao {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> list(Role entity) {
		return this.roleRepository.findAll();
	}

}
