package com.thinven.boot.domain.entity.employeeset.employeerole.repo;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.thinven.boot.domain.entity.employeeset.employeerole.EmployeeRole;
import com.thinven.boot.domain.entity.employeeset.employeerole.QEmployeeRole;
import com.thinven.boot.support.domain.entity.repository.WhereService;
import com.thinven.boot.support.domain.entity.repository.WhereServiceImpl;

public class EmployeeRoleRepositoryImpl extends QuerydslRepositorySupport implements EmployeeRoleRepositoryA {

	public EmployeeRoleRepositoryImpl() {
		super(EmployeeRole.class);
	}

	@Override
	public List<EmployeeRole> list(EmployeeRole employeerole) {
		return tmpl(employeerole, new WhereServiceImpl<EmployeeRole, QEmployeeRole>(employeerole) {
			public void where(QEmployeeRole qcc, JPQLQuery<EmployeeRole> query) {

			}
		});
	}

	private List<EmployeeRole> tmpl(EmployeeRole entity, WhereService<EmployeeRole, QEmployeeRole> where) {
		QEmployeeRole qcc = QEmployeeRole.employeeRole;

		JPQLQuery<EmployeeRole> query = from(qcc);

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
