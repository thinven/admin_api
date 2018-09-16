package com.thinven.boot.support.domain.entity.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thinven.boot.support.util.DateUtil;
import com.thinven.boot.support.util.ParamUtil;

@MappedSuperclass
public abstract class RegTimeEntityModel extends LastTimeEntityModel {

	@Temporal(TemporalType.TIMESTAMP)
	private Date regdate;

	//
	public void init() {
		this.setUid(ParamUtil.getUUID());
		this.setRegdate(DateUtil.now());
		this.setLastdate(DateUtil.now());
	}

	public abstract void setUid(String uid);

	// Get & Set
	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
