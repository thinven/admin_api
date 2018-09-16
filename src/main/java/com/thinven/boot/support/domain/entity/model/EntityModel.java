package com.thinven.boot.support.domain.entity.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class EntityModel extends RegTimeEntityModel {

	@Transient
	private String rk;

	// Get & Set
	@JsonIgnore
	public String getRk() {
		return rk;
	}

	public void setRk(String rk) {
		this.rk = rk;
	}

}
