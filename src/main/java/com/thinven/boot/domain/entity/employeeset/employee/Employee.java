package com.thinven.boot.domain.entity.employeeset.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinven.boot.support.domain.entity.model.EntityModel;

@Entity
@Table(name = "ma_employee")
public class Employee extends EntityModel {

	@Id
	@Column(name = "mem_uid")
	private String uid;
	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	private String birthday;
	@Column(name = "bcc_gender")
	private Long gender;

	// 관리자 페이지에서 등록시 employee_auth 전달용.
	private String id;

	// Constructor
	public Employee() {
		super();
	}

	public Employee(String p1, String p2, String p3) {
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}

	@JsonIgnore
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}