package com.thinven.boot.domain.entity.employeeset.employeeauth;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employeerole.EmployeeRole;
import com.thinven.boot.support.domain.entity.model.MemberModel;

@Entity
@Table(name = "ma_employee_auth")
@FilterDefs({ @FilterDef(name = "WITH_ROLE", parameters = @ParamDef(name = "memu", type = "long")) })
public class EmployeeAuth extends MemberModel {

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mem_uid")
	private Employee employee;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
	@Filter(name = "WITH_ROLE", condition = "mem_uid = :memu AND bcc_use = 1")
	private List<EmployeeRole> roles = new ArrayList<EmployeeRole>();

	// Constructor
	public EmployeeAuth() {
		super();
	}

	public EmployeeAuth(String p1, String p2, String p3) {
		this();
		this.setP1(p1);
		this.setP2(p2);
		this.setP3(p3);
	}

	public EmployeeAuth(String id, Employee info) {
		this();
		this.setId(id);
		this.setEmployee(info);
	}

	// Set & Get
	@JsonIgnore
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<EmployeeRole> getRoles() {
		return roles;
	}

	public void setRoles(List<EmployeeRole> roles) {
		this.roles = roles;
	}

}
