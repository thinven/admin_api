package com.thinven.boot.domain.entity.employeeset.employeeauth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employeeauth.EmployeeAuth;
import com.thinven.boot.domain.entity.employeeset.employeeauth.dao.EmployeeAuthDao;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.validator.DomainValidator;

@Component("employeeAuthValidator")
public class EmployeeAuthValidator extends DomainValidator<EmployeeAuth> {

	private Message<Employee> src;

	@Autowired
	private EmployeeAuthDao employeeAuthDao;

	public EmployeeAuthValidator src(Message<Employee> msg) {
		this.src = msg;
		this.setMsg(new Message<EmployeeAuth>());
		this.getMsg().setParams(new EmployeeAuth(msg.getParams().getId(), new Employee(msg.getParams().getUid())));
		return this;
	}

	public EmployeeAuthValidator required() {
		if (this.isOk()) {
			this.required(this.getParams().getId(), "EMPLOYEEAUTH_ID");
			if (this.src != null)
				this.src.setMsg(this.getMsg().getKey(), "EMPLOYEEAUTH_ID");
		}
		return this;
	}

	public EmployeeAuthValidator existEmployeeAuth() {
		if (this.isOk()) {
			Long count = this.employeeAuthDao.count(this.getParams());
			if (count.longValue() > 0) {
				this.setMsg("WAR_MSG_ALREADY_EXIST", "EMPLOYEEAUTH");
				if (this.src != null)
					this.src.setMsg("WAR_MSG_ALREADY_EXIST", "EMPLOYEEAUTH");
			}
		}
		return this;
	}

	public EmployeeAuthValidator duplicateId() {
		if (this.isOk()) {
			Long count = this.employeeAuthDao.countDuplicateId(this.getParams());
			if (count.longValue() > 0) {
				this.setMsg("WAR_MSG_ALREADY_EXIST", "EMPLOYEEAUTH_ID");
				if (this.src != null)
					this.src.setMsg("WAR_MSG_ALREADY_EXIST", "EMPLOYEEAUTH_ID");
			}
		}
		return this;
	}

}
