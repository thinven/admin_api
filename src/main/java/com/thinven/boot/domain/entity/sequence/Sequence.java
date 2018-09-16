package com.thinven.boot.domain.entity.sequence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thinven.boot.support.domain.entity.model.BaseModel;

@Entity
@Table(name = "ba_sequence")
public class Sequence extends BaseModel {
	@Id
	@Column(name = "bse_name")
	private String name;
	private Long nextvalue;

	//
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNextvalue() {
		return nextvalue;
	}

	public void setNextvalue(Long nextvalue) {
		this.nextvalue = nextvalue;
	}

}
