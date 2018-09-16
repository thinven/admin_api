package com.thinven.boot.domain.entity.employeeset.employeeauth.repo;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.thinven.boot.domain.entity.employeeset.employeeauth.EmployeeAuth;
import com.thinven.boot.domain.entity.employeeset.employeeauth.QEmployeeAuth;
import com.thinven.boot.support.domain.entity.repository.WhereService;
import com.thinven.boot.support.domain.entity.repository.WhereServiceImpl;

public class EmployeeAuthRepositoryImpl extends QuerydslRepositorySupport implements EmployeeAuthRepositoryA {

	public EmployeeAuthRepositoryImpl() {
		super(EmployeeAuth.class);
	}

	@Override
	public List<EmployeeAuth> list(EmployeeAuth employeeauth) {
		return tmpl(employeeauth, new WhereServiceImpl<EmployeeAuth, QEmployeeAuth>(employeeauth) {
			public void where(QEmployeeAuth qcc, JPQLQuery<EmployeeAuth> query) {

			}
		});
	}

	private List<EmployeeAuth> tmpl(EmployeeAuth entity, WhereService<EmployeeAuth, QEmployeeAuth> where) {
		QEmployeeAuth qcc = QEmployeeAuth.employeeAuth;

		JPQLQuery<EmployeeAuth> query = from(qcc);

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