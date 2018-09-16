package com.thinven.boot.domain.entity.employeeset.employeerole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.roleset.role.Role;
import com.thinven.boot.support.domain.entity.model.EntityModel;

@Entity
@Table(name = "ma_employee_role")
public class EmployeeRole extends EntityModel {

	@Id
	@Column(name = "mer_uid")
	private String uid;
	@Column(name = "bcc_use")
	private String use;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mem_uid")
	private Employee employee;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mro_uid")
	private Role role;

	// Constructor
	public EmployeeRole() {
		super();
	}

	public EmployeeRole(String p1, String p2, String p3) {
		this();
		this.setP1(p1);
		this.setP2(p2);
		this.setP3(p3);
	}

	// Set & Get
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	@JsonIgnore
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@JsonIgnore
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
