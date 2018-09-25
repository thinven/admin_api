package com.thinven.boot.domain.entity.commoncodeset.commoncode.validator;

import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.validator.DomainValidator;

@Component("commonCodeValidator")
public class CommonCodeValidator extends DomainValidator<CommonCode> {

	public CommonCodeValidator init(Message<CommonCode> msg) {
		this.setMsg(msg);
		return this;
	}

	public CommonCodeValidator required() {
		this.required(this.getParams().getBcgu(), "COMMONCODEGROUP_UID");
		this.required(this.getParams().getBcgn(), "COMMONCODEGROUP_NAME");
		this.required(this.getParams().getCode(), "COMMONCODE_CODE");
		this.required(this.getParams().getName(), "COMMONCODE_NAME");
		this.required(this.getParams().getOrdered(), "COMMONCODE_ORDERED");
		this.required(this.getParams().getUse(), "COMMONCODE_USE");
		return this;
	}

	public CommonCodeValidator requiredUid() {
		this.required(this.getParams().getUid(), "EMPLOYEE_UID");
		return this;
	}

}
