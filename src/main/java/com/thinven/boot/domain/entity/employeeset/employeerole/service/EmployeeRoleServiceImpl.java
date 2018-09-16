package com.thinven.boot.domain.entity.employeeset.employeerole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.employeeset.employeerole.EmployeeRole;
import com.thinven.boot.domain.entity.employeeset.employeerole.dao.EmployeeRoleDao;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;
import com.thinven.boot.support.log.Log;

@Service
@Transactional
public class EmployeeRoleServiceImpl extends BindService<EmployeeRole> implements EmployeeRoleService {

	@Autowired
	private EmployeeRoleDao employeeauthDao;

	@Override
	public Message<EmployeeRole> list(Message<EmployeeRole> msg) {
		if (msg.isOk()) {
			msg.add("employeeauthlist", this.employeeauthDao.list(msg.getParams()));
		}
		return msg;
	}

	@Override
	public Message<EmployeeRole> add(Message<EmployeeRole> msg) {
		if (msg.isOk()) {
			Log.info(this, "fire~~~~");
		}
		return msg;
	}

}
