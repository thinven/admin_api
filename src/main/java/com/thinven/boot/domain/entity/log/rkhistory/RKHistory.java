package com.thinven.boot.domain.entity.log.rkhistory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.support.domain.entity.model.LastTimeEntityModel;

@Entity
@Table(name = "lo_rk_history")
@TableGenerator(name = "lo_rk_history_seq_generator", pkColumnValue = "lo_rk_history", allocationSize = 1)
public class RKHistory extends LastTimeEntityModel {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "lo_rk_history_seq_generator")
	@Column(name = "lrh_seq")
	private Long seq;
	private String workdate;
	private String rk;
	private String pk;
	private String nick;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mme_seq")
	private Member member;

	// Constructor
	public RKHistory() {
		super();
	}

	public RKHistory(Member member) {
		this.member = member;
		this.rk = member.getRk();
		this.nick = member.getNick();
	}

	// Get & Set
	public Long getSeq() {
		if (this.seq == null)
			this.seq = 0L;
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	@JsonIgnore
	public String getWorkdate() {
		return workdate;
	}

	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}

	@JsonIgnore
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

	@JsonIgnore
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@JsonIgnore
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
