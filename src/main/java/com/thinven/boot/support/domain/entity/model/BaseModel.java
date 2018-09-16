package com.thinven.boot.support.domain.entity.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class BaseModel {

	@Transient
	private String workspace;
	@Transient
	private String rolescope;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	// Get & Set
	@JsonIgnore
	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	@JsonIgnore
	public String getRolescope() {
		return rolescope;
	}

	public void setRolescope(String rolescope) {
		this.rolescope = rolescope;
	}

}
