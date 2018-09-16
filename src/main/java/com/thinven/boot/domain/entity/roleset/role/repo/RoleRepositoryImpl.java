package com.thinven.boot.domain.entity.roleset.role.repo;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.thinven.boot.domain.entity.roleset.role.QRole;
import com.thinven.boot.domain.entity.roleset.role.Role;
import com.thinven.boot.support.domain.entity.repository.WhereService;
import com.thinven.boot.support.domain.entity.repository.WhereServiceImpl;

public class RoleRepositoryImpl extends QuerydslRepositorySupport implements RoleRepositoryA {

	public RoleRepositoryImpl() {
		super(Role.class);
	}

	@Override
	public List<Role> list(Role role) {
		return tmpl(role, new WhereServiceImpl<Role, QRole>(role) {
			public void where(QRole qcc, JPQLQuery<Role> query) {

			}
		});
	}

	private List<Role> tmpl(Role entity, WhereService<Role, QRole> where) {
		QRole qcc = QRole.role;

		JPQLQuery<Role> query = from(qcc);

		where.where(qcc, query);

		if (entity.getQs() != null && entity.getQs().length() > 0) {

		}

		query.orderBy(qcc.lastdate.asc());

		if (entity.getPage() > 0) {
			long offset = (entity.getPage() - 1) * entity.getPageSize();
			query.offset(offset);
			query.limit(entity.getPageSize());
		}

		return query.fetchResults().getResults();
	}
}
