package com.thinven.boot.domain.entity.employeeset.employee.validator;

import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.validator.DomainValidator;

@Component("employeeValidator")
public class EmployeeValidator extends DomainValidator<Employee> {

	public EmployeeValidator init(Message<Employee> msg) {
		this.setMsg(msg);
		return this;
	}

	public EmployeeValidator required() {
		this.required(this.getParams().getFirstname(), "EMPLOYEE_FIRSTNAME");
		this.required(this.getParams().getLastname(), "EMPLOYEE_LASTNAME");
		this.required(this.getParams().getBirthday(), "EMPLOYEE_BIRTHDAY");
		this.required(this.getParams().getGender(), "EMPLOYEE_GENDER");
		this.required(this.getParams().getPhone(), "EMPLOYEE_PHONE");
		this.required(this.getParams().getEmail(), "EMPLOYEE_EMAIL");
		return this;
	}

	public EmployeeValidator requiredUid() {
		this.required(this.getParams().getUid(), "EMPLOYEE_UID");
		return this;
	}

}
