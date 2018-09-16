package com.thinven.boot.domain.entity.memberset.member.validator;

import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.validator.EntityValidator;

@Component("checkpwMemberValidator")
public class CheckPWMemberValidator implements EntityValidator<Member> {

	@Override
	public Message<Member> validate(Message<Member> msg) {
		if (msg.isOk()) {
			if (msg.getParams().getPw().length() > 4) {
				// white list...
			} else {
				msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_PASSWORD");
			}
		}
		if (msg.isOk()) {
			if (msg.getParams().getPw2().equals(msg.getParams().getPw())) {
				// white list...
			} else {
				msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_PASSWORD2");
			}
		}
		return msg;
	}

}
