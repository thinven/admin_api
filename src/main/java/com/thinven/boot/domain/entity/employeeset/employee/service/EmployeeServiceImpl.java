package com.thinven.boot.domain.entity.employeeset.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employee.dao.EmployeeDao;
import com.thinven.boot.domain.entity.employeeset.employee.validator.EmployeeValidator;
import com.thinven.boot.domain.entity.employeeset.employeeauth.service.EmployeeAuthService;
import com.thinven.boot.domain.entity.employeeset.employeeauth.validator.EmployeeAuthValidator;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;

@Service
@Transactional
public class EmployeeServiceImpl extends BindService<Employee> implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private EmployeeValidator employeValidator;

	@Autowired
	private EmployeeAuthService employeeAuthService;
	@Autowired
	private EmployeeAuthValidator employeeAuthValidator;

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
		msg = this.employeValidator.init(msg).required().result();
		this.employeeAuthValidator.src(msg).required().duplicateId().existEmployeeAuth();

		if (msg.isOk()) {
			Employee info = this.employeeDao.add(msg.getParams());
			msg.add("employee", info);

			msg = employeeAuthService.add(msg, info);
		}
		return msg;
	}

	@Override
	public Message<Employee> update(Message<Employee> msg) {
		msg = this.employeValidator.init(msg).required().result();
		this.employeeAuthValidator.src(msg).required().duplicateId();

		if (msg.isOk()) {
			Employee info = this.employeeDao.info(msg.getParams());
			if (info != null) {
				info.setFirstname(msg.getParams().getFirstname());
				info.setLastname(msg.getParams().getLastname());
				info.setBirthday(msg.getParams().getBirthday());
				info.setGender(msg.getParams().getGender());
				info.setPhone(msg.getParams().getPhone());
				info.setEmail(msg.getParams().getEmail());
				msg.add("employee", info);

				msg = this.employeeAuthService.update(msg, msg.getParams());
			}
		}
		return msg;
	}

	@Override
	public Message<Employee> delete(Message<Employee> msg) {
		msg = this.employeValidator.init(msg).requiredUid().result();

		if (msg.isOk()) {
			Employee info = this.employeeDao.info(msg.getParams());
			if (info != null) {
				info.setDelete(10L);
				msg.add("employee", info);
			}
		}
		return msg;
	}

}
