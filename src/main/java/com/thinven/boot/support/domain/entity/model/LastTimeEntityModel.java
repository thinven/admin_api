package com.thinven.boot.support.domain.entity.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class LastTimeEntityModel extends PageModel {

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastdate;

	public void setLast() {
		this.setLastdate(new Date());
	}

	// Get & Set
	public Date getLastdate() {
		return lastdate;
	}

	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}

}
