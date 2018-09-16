package com.thinven.boot.support.domain.entity.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class VOTypeEntityModel extends LastTimeEntityModel {

	@Transient
	private String rk;
	@Transient
	private String votype;

	// Get & Set
	@JsonIgnore
	public String getRk() {
		return rk;
	}

	public void setRk(String rk) {
		this.rk = rk;
	}

	@JsonIgnore
	public String getVotype() {
		return votype;
	}

	public void setVotype(String votype) {
		this.votype = votype;
	}

}
