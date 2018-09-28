package com.thinven.boot.domain.entity.commoncodeset.commoncode;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private Long ordered;
	@Column(name = "bcc_use")
	private Long use;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bcg_uid")
	private CommonCodeGroup commonCodeGroup;

	@Transient
	private String bcgu;
	@Transient
	private String bcgn;

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

	public CommonCode(Map<String, Object> payload) {
		this();
		this.setP1(payload.get("p1").toString());
		this.setP2(payload.get("p2").toString());
		this.setP3(payload.get("p3").toString());
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

	public CommonCodeGroup getCommonCodeGroup() {
		return commonCodeGroup;
	}

	public void setCommonCodeGroup(CommonCodeGroup commonCodeGroup) {
		this.commonCodeGroup = commonCodeGroup;
	}

	@JsonIgnore
	public String getBcgu() {
		return bcgu;
	}

	public void setBcgu(String bcgu) {
		this.bcgu = bcgu;
	}

	@JsonIgnore
	public String getBcgn() {
		return bcgn;
	}

	public void setBcgn(String bcgn) {
		this.bcgn = bcgn;
	}

}
