package com.thinven.boot.domain.entity.roleset.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thinven.boot.support.domain.entity.model.EntityModel;

@Entity
@Table(name = "ma_role")
public class Role extends EntityModel {

	@Id
	@Column(name = "mro_uid")
	private String uid;
	private String name;

	// Constructor
	public Role() {
		super();
	}

	public Role(String p1, String p2, String p3) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
