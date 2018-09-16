package com.thinven.boot.domain.entity.log.waserror;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinven.boot.support.domain.entity.model.LastTimeEntityModel;

@Entity
@Table(name = "lo_was_error")
@TableGenerator(name = "lo_was_error_seq_generator", pkColumnValue = "lo_was_error", allocationSize = 1)
public class WasError<G> extends LastTimeEntityModel {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "lo_was_error_seq_generator")
	@Column(name = "lwe_seq")
	private Long seq;
	private String workdate;
	private String requesturi;
	private String entitystring;
	private String simplemsg;
	private String errormsg;

	// Constructor
	public WasError() {
		super();
	}

	public WasError(G entity, Exception ex) {
		this.simplemsg = ex.toString();
		this.entitystring = entity.toString();
		this.errormsg = ExceptionUtils.getStackTrace(ex);
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
	public String getRequesturi() {
		return requesturi;
	}

	public void setRequesturi(String requesturi) {
		this.requesturi = requesturi;
	}

	@JsonIgnore
	public String getEntitystring() {
		return entitystring;
	}

	public void setEntitystring(String entitystring) {
		this.entitystring = entitystring;
	}

	@JsonIgnore
	public String getSimplemsg() {
		return simplemsg;
	}

	public void setSimplemsg(String simplemsg) {
		this.simplemsg = simplemsg;
	}

	@JsonIgnore
	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

}
