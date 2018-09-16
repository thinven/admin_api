package com.thinven.boot.domain.entity.roleset.role.service;

import com.thinven.boot.domain.entity.roleset.role.Role;
import com.thinven.boot.support.domain.entity.service.AddService;
import com.thinven.boot.support.domain.entity.service.ListService;

public interface RoleService extends ListService<Role>, AddService<Role> {

	public static final long YES = 1;
	public static final long NO = 0;

}
