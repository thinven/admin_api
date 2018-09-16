package com.thinven.boot.domain.entity.log.userrequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinven.boot.support.domain.entity.model.LastTimeEntityModel;

@Entity
@Table(name = "lo_user_request")
@TableGenerator(name = "lo_user_request_seq_generator", pkColumnValue = "lo_user_request", allocationSize = 1)
public class UserRequest<G> extends LastTimeEntityModel {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "lo_user_request_seq_generator")
	@Column(name = "lur_seq")
	private Long seq;
	private String workdate;
	private String requesturi;
	private String entitystring;
	private String rk;

	// Constructor
	public UserRequest() {
		super();
	}

	public UserRequest(G entity) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.rk = this.getRk(entity);
		this.entitystring = entity.toString();
	}

	private String getRk(G entity) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String result = "";
		Method m = entity.getClass().getMethod("getRk", new Class[] {});
		if (m != null) {
			result = (String) m.invoke(entity, new Object[] {});
		}
		return result;
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
	public String getRk() {
		return rk;
	}

	public void setRk(String rk) {
		this.rk = rk;
	}

}
