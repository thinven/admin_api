package com.thinven.boot.domain.entity.roleset.role.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.roleset.role.Role;
import com.thinven.boot.domain.entity.roleset.role.dao.RoleDao;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;
import com.thinven.boot.support.log.Log;

@Service
@Transactional
public class RoleServiceImpl extends BindService<Role> implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Message<Role> list(Message<Role> msg) {
		if (msg.isOk()) {
			msg.add("roleList", this.roleDao.list(msg.getParams()));
		}
		return msg;
	}

	@Override
	public Message<Role> add(Message<Role> msg) {
		if (msg.isOk()) {
			Log.info(this, "fire~~~~");
		}
		return msg;
	}

}
