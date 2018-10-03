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
			public void where(QRole qr, JPQLQuery<Role> query) {

			}
		});
	}

	private List<Role> tmpl(Role entity, WhereService<Role, QRole> where) {
		QRole qr = QRole.role;

		JPQLQuery<Role> query = from(qr);

		where.where(qr, query);

		if (entity.getName() != null && entity.getName().length() > 0) {
			query.where(qr.name.startsWith(entity.getName()));
		}

		query.orderBy(qr.lastdate.asc());

		if (entity.getPage() > 0) {
			long offset = (entity.getPage() - 1) * entity.getPageSize();
			query.offset(offset);
			query.limit(entity.getPageSize());
		}

		return query.fetchResults().getResults();
	}
}
