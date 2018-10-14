package com.thinven.boot.support.domain.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class MemberModel extends RegTimeEntityModel {

	@Id
	@Column(name = "mea_uid")
	private String uid;
	private String id;
	private String pw;
	private String rk;// 관계키 : 인증토큰으로 사용.
	private String pk;// 암호 개인키.

	public MemberModel() {
		super();
	}

	public MemberModel(String rk) {
		this();
		this.uid = rk;
		this.id = rk;
		this.pw = rk;
		this.rk = rk;
		this.pk = rk;
	}

	// Get & Set
	@JsonIgnore
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonIgnore
	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getRk() {
		return rk;
	}

	public void setRk(String rk) {
		this.rk = rk;
	}

	@JsonIgnore
	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

}
