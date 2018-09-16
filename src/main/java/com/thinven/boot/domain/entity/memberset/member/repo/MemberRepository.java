package com.thinven.boot.domain.entity.memberset.member.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.thinven.boot.domain.entity.memberset.member.Member;

public interface MemberRepository extends JpaRepository<Member, String>, JpaSpecificationExecutor<Member>, MemberRepositoryA {

	public Member findByUid(String uid);

	public Member findByRk(String rk);

}
