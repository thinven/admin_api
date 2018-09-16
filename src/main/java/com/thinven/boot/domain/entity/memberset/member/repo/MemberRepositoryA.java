package com.thinven.boot.domain.entity.memberset.member.repo;

import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.support.domain.entity.repository.ListRepository;

public interface MemberRepositoryA extends ListRepository<Member> {

	public Member info(Member member);

	public Member login(Member member);

}
