package com.thinven.boot.support.domain.entity.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class SecurityModel extends BaseModel {

	@Transient
	private String p1;
	@Transient
	private String p2;
	@Transient
	private String p3;
	@Transient
	private String privateKey;
	@Transient
	private String pkm;
	@Transient
	private String pke;

	// Get & Set
	@JsonIgnore
	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	@JsonIgnore
	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	@JsonIgnore
	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	@JsonIgnore
	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	@JsonIgnore
	public String getPkm() {
		return pkm;
	}

	public void setPkm(String pkm) {
		this.pkm = pkm;
	}

	@JsonIgnore
	public String getPke() {
		return pke;
	}

	public void setPke(String pke) {
		this.pke = pke;
	}

}
