package com.thinven.boot.domain.entity.commoncodeset.commoncodegroup;

import java.util.Map;

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
	private Long ordered;
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

	public CommonCodeGroup(Map<String, Object> payload) {
		this();
		this.setP1(payload.get("p1").toString());
		this.setP2(payload.get("p2").toString());
		this.setP3(payload.get("p3").toString());
	}

	public CommonCodeGroup(String bcgu) {
		this();
		this.uid = bcgu;
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

	public Long getOrdered() {
		return ordered;
	}

	public void setOrdered(Long ordered) {
		this.ordered = ordered;
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
