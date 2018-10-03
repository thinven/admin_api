package com.thinven.boot.domain.entity.roleset.role.service;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.roleset.role.Role;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.AddService;
import com.thinven.boot.support.domain.entity.service.ListService;

public interface RoleService extends ListService<Role>, AddService<Role> {

	Message<Employee> makeJson(Message<Employee> msg);

}
