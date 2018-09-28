package com.thinven.boot.domain.entity.memberset.member.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CC;
import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.domain.entity.memberset.member.repo.MemberRepository;
import com.thinven.boot.domain.entity.memberset.member.service.MemberService;
import com.thinven.boot.support.domain.entity.dao.EntityDao;
import com.thinven.boot.support.security.SHA512;
import com.thinven.boot.support.util.DateUtil;
import com.thinven.boot.support.util.ParamUtil;

@Component("memberDao")
public class MemberDaoImpl extends EntityDao<Member> implements MemberDao {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public List<Member> list(Member member) {
		return this.memberRepository.list(member);
	}

	public Member add(Member member) {
		member.init();
		member.setPw(SHA512.getDigest(member.getPw()));
		member.setNick(this.newNick());
		member.setRk(this.newRk());
		member.setGoodbye(CC.NO);
		member.setGoodbyetime(DateUtil.add(new Date(), MemberService.FOREVER_DAYS));
		return this.memberRepository.save(member);
	}

	@Override
	public Member info(Member member) {
		return this.memberRepository.info(member);
	}

	@Override
	public Member infoByRk(Member member) {
		Member info = this.memberRepository.findByRk(member.getRk());
		if (info != null && info.isLogin()) {
			info.setLast();
		} else {
			info = this.memberRepository.findByUid(MemberService.GUEST_UID);
		}
		return info;
	}

	@Override
	public Member infoById(Member member) {
		return this.memberRepository.info(member);
	}

	@Override
	public Member infoByNick(Member member) {
		return this.memberRepository.info(member);
	}

	@Override
	public Member infoByIdAndPw(Member member) {
		return this.memberRepository.login(member);
	}

	@Override
	public String newRk() {
		return ParamUtil.getRandomKey(8121, 32);
	}

	private String newNick() {
		return "nick" + ParamUtil.getRandomKey(2209, 6);
	}
}
