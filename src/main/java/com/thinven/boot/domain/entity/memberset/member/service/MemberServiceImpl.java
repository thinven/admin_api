package com.thinven.boot.domain.entity.memberset.member.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CC;
import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.domain.entity.memberset.member.dao.MemberDao;
import com.thinven.boot.support.domain.entity.model.MemberModel;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;
import com.thinven.boot.support.domain.entity.validator.EntityValidator;
import com.thinven.boot.support.security.SHA512;
import com.thinven.boot.support.util.DateUtil;

@Service
@Transactional
public class MemberServiceImpl extends BindService<Member> implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private EntityValidator<Member> checkidMemberValidator;
	@Autowired
	private EntityValidator<Member> duplicationidMemberValidator;
	@Autowired
	private EntityValidator<Member> checkpwMemberValidator;
	@Autowired
	private EntityValidator<Member> isMemberValidator;
	@Autowired
	private EntityValidator<Member> duplicationnickMemberValidator;
	@Autowired
	private EntityValidator<Member> existnickMemberValidator;
	@Autowired
	private EntityValidator<Member> equalspwMemberValidator;

	@Override
	public Message<Member> list(Message<Member> msg) {
		msg = this.bindP1(msg, this);

		msg = this.isMember(msg);

		if (msg.isOk()) {
			msg.add("list", this.memberDao.list(msg.getParams()));
		}
		return msg;
	}

	@Override
	public Message<Member> add(Message<Member> msg) {
		msg = this.bindP1(msg, this);

		msg = this.checkidMemberValidator.validate(msg);
		msg = this.duplicationidMemberValidator.validate(msg);
		msg = this.checkpwMemberValidator.validate(msg);

		if (msg.isOk()) {
			this.memberDao.add(msg.getParams());
		}
		return msg;
	}

	@Override
	public Message<Member> info(Message<Member> msg) {
		msg = this.bindP1(msg, this);

		if (msg.isOk()) {
			Member member = msg.getParams();
			if (MemberService.JOIN_INFO.equals(member.getWorkspace())) {
			} else if (MemberService.EDIT_INFO.equals(member.getWorkspace())) {
				msg = this.isMemberValidator.validate(msg);
			} else if (MemberService.FIND_ID.equals(member.getWorkspace())) {
			} else if (MemberService.FIND_PW.equals(member.getWorkspace())) {
			}
		}
		return msg;
	}

	@Override
	public Member infoByRk(Member member) {
		return this.memberDao.infoByRk(member);
	}

	@Override
	public MemberModel infoByRk(String rk) {
		return this.memberDao.infoByRk(new Member(rk));
	}

	@Override
	public Message<Member> check(Message<Member> msg) {
		msg = this.bindP1(msg, this);

		if (MemberService.PICK_RECEIVER.equals(msg.getParams().getWorkspace())) {
			msg = this.existnickMemberValidator.validate(msg);
		} else {
			msg = this.duplicationnickMemberValidator.validate(msg);
		}

		return msg;
	}

	@Override
	public Message<Member> update(Message<Member> msg) {
		msg = this.bindP1(msg, this);

		msg = this.isMember(msg);

		if (msg.isOk()) {
			Member member = msg.getParams();
			if (MemberService.GOODBYE.equals(member.getWorkspace())) {
				this.goodbye(msg);
			} else if (MemberService.GOODBYE_CANCEL.equals(member.getWorkspace())) {
				this.goodbyecancel(msg);
			} else if (MemberService.EDIT_INFO.equals(member.getWorkspace())) {
				this.editmember(msg);
			} else if (MemberService.EDIT_PASSWORD.equals(member.getWorkspace())) {
				this.editpw(msg);
			}
		}
		return msg;
	}

	// ///////////////////////////////////////////////////////////////////////////////

	private Message<Member> editpw(Message<Member> msg) {

		msg = this.equalsPW(msg);

		if (msg.isOk()) {
			Member info = msg.getInfo();
			info.setPw(SHA512.getDigest(msg.getParams().getNpw()));
			info.setLast();
		}
		return msg;
	}

	private Message<Member> editmember(Message<Member> msg) {

		msg = this.checkidMemberValidator.validate(msg);
		msg = this.duplicationidMemberValidator.validate(msg);

		if (msg.isOk()) {
			Member info = msg.getInfo();
			info.setLast();
		}
		return msg;
	}

	private Message<Member> goodbye(Message<Member> msg) {
		Member info = msg.getInfo();
		if (info != null) {
			info.setGoodbye(CC.YES);
			info.setGoodbyetime(DateUtil.add(new Date(), MemberService.GOODBYE_DAYS));
		}
		return msg;
	}

	private Message<Member> goodbyecancel(Message<Member> msg) {
		Member info = msg.getInfo();
		if (info != null) {
			info.setGoodbye(CC.NO);
			info.setGoodbyetime(DateUtil.add(new Date(), MemberService.FOREVER_DAYS));
		}
		return msg;
	}

	@Override
	public Message<Member> isMember(Message<Member> msg) {
		return this.isMemberValidator.validate(msg);
	}

	@Override
	public Message<Member> equalsPW(Message<Member> msg) {
		return this.equalspwMemberValidator.validate(msg);
	}

}
