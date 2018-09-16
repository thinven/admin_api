package com.thinven.boot.domain.entity.employeeset.employeerole.service;

import com.thinven.boot.domain.entity.employeeset.employeerole.EmployeeRole;
import com.thinven.boot.support.domain.entity.service.AddService;
import com.thinven.boot.support.domain.entity.service.ListService;

public interface EmployeeRoleService extends ListService<EmployeeRole>, AddService<EmployeeRole> {

	public static final long YES = 1;
	public static final long NO = 0;

}
