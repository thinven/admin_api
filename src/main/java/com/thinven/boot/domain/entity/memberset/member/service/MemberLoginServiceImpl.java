package com.thinven.boot.domain.entity.memberset.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.log.rkhistory.RKHistory;
import com.thinven.boot.domain.entity.log.rkhistory.service.RKHistoryService;
import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.domain.entity.memberset.member.dao.MemberDao;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.model.SecurityModel;
import com.thinven.boot.support.domain.entity.service.BindService;
import com.thinven.boot.support.security.RsaUtil;
import com.thinven.boot.support.security.SHA512;

@Service
@Transactional
public class MemberLoginServiceImpl extends BindService<Member> implements MemberLoginService {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private MemberService memberService;

	@Autowired
	private RKHistoryService rkhistoryService;

	@Override
	public Message<Member> info(Message<Member> msg) {
		msg = this.bindP1(msg, this.memberService);

		if (msg.isOk()) {
			Member member = msg.getParams();
			if (MemberService.BACK_OFFICE.equals(member.getWorkspace())) {
				this.loginB(msg);
			} else {
				this.login(msg);
			}
		}

		return msg;
	}

	private void login(Message<Member> msg) {
		Member member = msg.getParams();
		member.setPw(SHA512.getDigest(member.getPw()));
		Member info = this.memberDao.infoByIdAndPw(member);
		if (info != null && info.isLogin()) {
			msg.setInfo(info);
			this.set(msg);

			this.rkhistoryService.add(new Message<RKHistory>());
			if (member.getPushtoken() != null && member.getPushtoken().length() > 0) {
			}
		} else {
			msg.setMsg("WAR_MSG_DATA_NOT_FOUND", "PARAM_MEMBER");
		}
	}

	private void loginB(Message<Member> msg) {
		Member member = msg.getParams();
		member.setPw(SHA512.getDigest(member.getPw()));
		Member info = this.memberDao.infoByIdAndPw(member);
		if (info != null && info.isLogin()) {
			boolean isBack = false;

			if (isBack) {
				msg.setInfo(info);
				this.set(msg);

				// this.rkhistoryService.add(msg, new RKHistory(info), request);
			} else {
				msg.setMsg("WAR_MSG_DATA_NOT_FOUND", "PARAM_MEMBER");
			}
		} else {
			msg.setMsg("WAR_MSG_DATA_NOT_FOUND", "PARAM_MEMBER");
		}
	}

	private void set(Message<Member> msg) {
		Member info = msg.getInfo();
		info.setRk(this.memberDao.newRk());
		info.setLast();
		msg.add("rk", info.getRk());
		msg.add("id", info.getId());

		SecurityModel model = RsaUtil.generateKey();
		info.setPk(model.getPrivateKey());
		msg.add("pkm2", model.getPkm());
		msg.add("pke2", model.getPke());
	}
}
