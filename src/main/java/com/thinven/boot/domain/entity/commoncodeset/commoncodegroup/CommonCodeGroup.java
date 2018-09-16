package com.thinven.boot.domain.entity.commoncodeset.commoncodegroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thinven.boot.support.domain.entity.model.EntityModel;

@Entity
@Table(name = "ba_common_code_group")
public class CommonCodeGroup extends EntityModel {

	@Id
	@Column(name = "bcg_uid")
	private String uid;
	private String name;
	private Long order;
	@Column(name = "bcc_use")
	private Long use;
	@Column(name = "bcc_cache")
	private Long cache;

	// Constructor
	public CommonCodeGroup() {
		super();
	}

	public CommonCodeGroup(String p1, String p2, String p3) {
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

	public Long getCache() {
		return cache;
	}

	public void setCache(Long cache) {
		this.cache = cache;
	}

}
