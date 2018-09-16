package com.thinven.boot.domain.entity.memberset.member.validator;

import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.validator.EntityValidator;

@Component("existnickMemberValidator")
public class ExistNickMemberValidator implements EntityValidator<Member> {

	@Override
	public Message<Member> validate(Message<Member> msg) {

		if (msg.isOk()) {
			if (msg.getInfo() != null) {
				// white list...
			} else {
				msg.setMsg("WAR_MSG_DATA_NOT_FOUND", "PARAM_NICK");
			}
		}
		return msg;
	}

}
