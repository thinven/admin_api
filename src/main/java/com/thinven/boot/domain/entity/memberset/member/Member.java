package com.thinven.boot.domain.entity.memberset.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinven.boot.domain.entity.commoncodeset.commoncode.CC;
import com.thinven.boot.support.domain.entity.model.MemberModel;

@Entity
@Table(name = "ma_member")
/**
 * RegTimeEntityModel 을 상속한 이유는 rk 멤버변수의 충돌 때문에.
 * 
 * @author needthink
 *
 */
public class Member extends MemberModel {

	private String nick;
	@Column(name = "bcc_goodbye")
	private Long goodbye;
	@Temporal(TemporalType.TIMESTAMP)
	private Date goodbyetime;

	//
	@Transient
	private String pw2;
	@Transient
	private String npw;// 비번 변경에서 사용
	@Transient
	private String npw2;// 비번 변경에서 사용
	@Transient
	private boolean isLogin;
	@Transient
	private Long bros;
	@Transient
	private String pushtoken;// 로그인시 설정.

	// Constructor
	public Member() {

	}

	public Member(String rk) {
		this();
		this.setRk(rk);
	}

	// Get & Set
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@JsonIgnore
	public Long getGoodbye() {
		return goodbye;
	}

	public void setGoodbye(Long goodbye) {
		this.goodbye = goodbye;
	}

	public Date getGoodbyetime() {
		return goodbyetime;
	}

	public void setGoodbyetime(Date goodbyetime) {
		this.goodbyetime = goodbyetime;
	}

	@JsonIgnore
	public String getPw2() {
		return pw2;
	}

	public void setPw2(String pw2) {
		this.pw2 = pw2;
	}

	@JsonIgnore
	public String getNpw() {
		return npw;
	}

	public void setNpw(String npw) {
		this.npw = npw;
	}

	@JsonIgnore
	public String getNpw2() {
		return npw2;
	}

	public void setNpw2(String npw2) {
		this.npw2 = npw2;
	}

	public boolean isLogin() {
		return !"".equals(this.getUid()) && this.goodbye == CC.NO;
	}

	@JsonIgnore
	public Long getBros() {
		return bros;
	}

	public void setBros(Long bros) {
		this.bros = bros;
	}

	@JsonIgnore
	public String getPushtoken() {
		return pushtoken;
	}

	public void setPushtoken(String pushtoken) {
		this.pushtoken = pushtoken;
	}

}
