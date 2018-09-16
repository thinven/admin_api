package com.thinven.boot.domain.entity.memberset.member.validator;

import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.validator.EntityValidator;
import com.thinven.boot.support.security.SHA512;

@Component("equalspwMemberValidator")
public class EqualsPWMemberValidator implements EntityValidator<Member> {

	@Override
	public Message<Member> validate(Message<Member> msg) {
		if (msg.isOk()) {
			if (msg.getInfo().getPw().equals(SHA512.getDigest(msg.getParams().getPw()))) {
				// white list.
			} else {
				msg.setMsg("WAR_MSG_NOT_EQUALS", "PARAM_PASSWORD");
			}
		}
		return msg;
	}

}
