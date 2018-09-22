package com.thinven.boot.support.domain.entity.validator;

import com.thinven.boot.support.domain.entity.model.Message;

public abstract class DomainValidator<T> {

	private Message<T> msg = null;

	public boolean isOk() {
		return this.msg.isOk();
	}

	public T getParams() {
		return this.msg.getParams();
	}

	public void required(String val, String strParam) {
		if (this.msg.isOk()) {
			if (val != null && val.length() > 0) {
				// white
			} else {
				this.setMsg("WAR_MSG_INPUT_EMPTY", strParam);
			}
		}
	}

	public void required(Long val, String strParam) {
		if (this.msg.isOk()) {
			if (val != null && val.longValue() > 0) {
				// white
			} else {
				this.setMsg("WAR_MSG_INPUT_EMPTY", strParam);
			}
		}
	}

	public Message<T> result() {
		return msg;
	}

	public Message<T> getMsg() {
		return msg;
	}

	public void setMsg(Message<T> msg) {
		this.msg = msg;
	}

	public void setMsg(String key, String param) {
		this.msg.setMsg(key, param);
	}

}
