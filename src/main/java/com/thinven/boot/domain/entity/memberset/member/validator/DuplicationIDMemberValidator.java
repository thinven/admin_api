package com.thinven.boot.domain.entity.memberset.member.validator;

import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.validator.EntityValidator;

@Component("duplicationidMemberValidator")
public class DuplicationIDMemberValidator implements EntityValidator<Member> {

	@Override
	public Message<Member> validate(Message<Member> msg) {
		if (msg.isOk()) {
			if (msg.getInfo() == null) {
				// white list...
			} else {
				msg.setMsg("WAR_MSG_ALREADY_EXIST", "PARAM_EMAIL");
			}
		}
		return msg;
	}

}
