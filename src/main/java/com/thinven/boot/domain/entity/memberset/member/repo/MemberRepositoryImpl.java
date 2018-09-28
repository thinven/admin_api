package com.thinven.boot.domain.entity.memberset.member.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import com.querydsl.jpa.JPQLQuery;
import com.thinven.boot.domain.entity.commoncodeset.commoncode.CC;
import com.thinven.boot.domain.entity.memberset.member.Member;
import com.thinven.boot.domain.entity.memberset.member.QMember;
import com.thinven.boot.support.domain.entity.repository.WhereService;
import com.thinven.boot.support.domain.entity.repository.WhereServiceImpl;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryA {

	public MemberRepositoryImpl() {
		super(Member.class);
	}

	@Override
	public List<Member> list(Member entity) {
		return tmpl(entity, new WhereServiceImpl<Member, QMember>(entity) {
			public void where(QMember qm, JPQLQuery<Member> query) {

			}
		});
	}

	@Override
	public Member info(Member member) {
		QMember qm = QMember.member;

		JPQLQuery<Member> query = from(qm);

		if (StringUtils.hasText(member.getUid())) {
			query.where(qm.uid.eq(member.getUid()));
		} else if (StringUtils.hasText(member.getId())) {
			query.where(qm.id.eq(member.getId()));
		} else if (StringUtils.hasText(member.getNick())) {
			query.where(qm.nick.eq(member.getNick()));
		} else if (StringUtils.hasText(member.getRk())) {
			query.where(qm.rk.eq(member.getRk()));
		}

		query.where(qm.goodbye.eq(CC.NO)
				.or(qm.goodbye.eq(CC.YES).and(qm.goodbyetime.gt(new Date()))));

		return query.fetchFirst();
	}

	@Override
	public Member login(Member member) {
		QMember qm = QMember.member;

		JPQLQuery<Member> query = from(qm);

		query.where(qm.id.eq(member.getId()));
		query.where(qm.pw.eq(member.getPw()));
		query.where(qm.goodbyetime.gt(new Date()));

		return query.fetchFirst();
	}

	private List<Member> tmpl(Member entity, WhereService<Member, QMember> where) {
		QMember qm = QMember.member;

		JPQLQuery<Member> query = from(qm);

		where.where(qm, query);

		if (entity.getQs().length() > 0) {
			query.where(qm.nick.contains(entity.getQs()).or(qm.id.contains(entity.getQs())));
		}

		query.orderBy(qm.lastdate.desc());

		if (entity.getPage() > 0) {
			long offset = (entity.getPage() - 1) * entity.getPageSize();
			query.offset(offset);
			query.limit(entity.getPageSize());
		}

		return query.fetchResults().getResults();
	}
}
