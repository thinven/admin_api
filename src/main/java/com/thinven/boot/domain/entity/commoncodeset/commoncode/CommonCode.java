package com.thinven.boot.domain.entity.commoncodeset.commoncode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.CommonCodeGroup;
import com.thinven.boot.support.domain.entity.model.EntityModel;

@Entity
@Table(name = "ba_common_code")
public class CommonCode extends EntityModel {
	@Id
	@Column(name = "bcc_uid")
	private String uid;
	private Long code;
	private String name;
	private Long order;
	@Column(name = "bcc_use")
	private Long use;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bcg_uid")
	private CommonCodeGroup commoncodegroup;

	// Constructor
	public CommonCode() {
		super();
	}

	public CommonCode(String p1, String p2, String p3) {
		this();
		this.setP1(p1);
		this.setP2(p2);
		this.setP3(p3);
	}

	// Get & Set
	public String getUid() {
		if (this.uid == null)
			this.uid = "";
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public Long getUse() {
		return use;
	}

	public void setUse(Long use) {
		this.use = use;
	}

	@JsonIgnore
	public CommonCodeGroup getCommoncodegroup() {
		return commoncodegroup;
	}

	public void setCommoncodegroup(CommonCodeGroup commoncodegroup) {
		this.commoncodegroup = commoncodegroup;
	}

}
