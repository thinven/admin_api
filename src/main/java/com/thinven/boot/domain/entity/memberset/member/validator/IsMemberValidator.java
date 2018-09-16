package com.thinven.boot.domain.entity.memberset.member.validator;

import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.validator.EntityValidator;

@Component("isMemberValidator")
public class IsMemberValidator implements EntityValidator<Member> {

	@Override
	public Message<Member> validate(Message<Member> msg) {
		if (msg.isOk()) {
			if (msg.getInfo().isLogin()) {
				// white list.
			} else {
				msg.setMsg("WAR_MSG_NEED_MEMBER");
			}
		}
		return msg;
	}

}
