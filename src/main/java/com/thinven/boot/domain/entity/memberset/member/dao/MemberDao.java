package com.thinven.boot.domain.entity.memberset.member.dao;

import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.support.domain.entity.dao.AddDao;
import com.thinven.boot.support.domain.entity.dao.InfoDao;
import com.thinven.boot.support.domain.entity.dao.ListDao;

public interface MemberDao extends ListDao<Member>, AddDao<Member>, InfoDao<Member> {

	public Member infoByRk(Member member);

	public Member infoById(Member member);

	public Member infoByNick(Member member);

	public Member infoByIdAndPw(Member member);

	public String newRk();

}
