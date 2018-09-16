package com.thinven.boot.domain.entity.memberset.member.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.validator.EntityValidator;

@Component("checkidMemberValidator")
public class CheckIDMemberValidator implements EntityValidator<Member> {

	@Override
	public Message<Member> validate(Message<Member> msg) {
		if (msg.isOk()) {
			Pattern p = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
			Matcher m = p.matcher(msg.getParams().getId());
			if (m.matches()) {
				// white list...
			} else {
				msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_EMAIL");
			}
		}
		return msg;
	}

}
