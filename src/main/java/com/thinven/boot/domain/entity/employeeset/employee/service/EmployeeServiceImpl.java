package com.thinven.boot.domain.entity.employeeset.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employee.dao.EmployeeDao;
import com.thinven.boot.domain.entity.employeeset.employeeauth.service.EmployeeAuthService;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;

@Service
@Transactional
public class EmployeeServiceImpl extends BindService<Employee> implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private EmployeeAuthService employeeAuthService;

	@Override
	public Message<Employee> list(Message<Employee> msg) {
		if (msg.isOk()) {
			msg.add("employeePages", msg.getParams().getPages(this.employeeDao.count(msg.getParams())));
			msg.add("employeeList", this.employeeDao.list(msg.getParams()));
		}
		return msg;
	}

	@Override
	public Message<Employee> add(Message<Employee> msg) {
		if (msg.isOk()) {
			Employee info = this.employeeDao.add(msg.getParams());
			msg.add("employee", info);

			msg = employeeAuthService.add(msg, info);
		}
		return msg;
	}

}
