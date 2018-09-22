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
	public List<EmployeeAuth> list(EmployeeAuth employeeAuth) {
		return tmpl(employeeAuth, new WhereServiceImpl<EmployeeAuth, QEmployeeAuth>(employeeAuth) {
			public void where(QEmployeeAuth qea, JPQLQuery<EmployeeAuth> query) {

			}
		});
	}

	@Override
	public Long count(EmployeeAuth entity) {
		return tmplCount(entity, new WhereServiceImpl<EmployeeAuth, QEmployeeAuth>(entity) {
			public void where(QEmployeeAuth qea, JPQLQuery<EmployeeAuth> query) {
				if (entity.getEmployee() != null && entity.getEmployee().getUid() != null && entity.getEmployee().getUid().length() > 0) {
					query.where(qea.employee.uid.eq(entity.getEmployee().getUid()));
				}
			}
		});
	}

	@Override
	public Long countDuplicateId(EmployeeAuth entity) {
		return tmplCount(entity, new WhereServiceImpl<EmployeeAuth, QEmployeeAuth>(entity) {
			public void where(QEmployeeAuth qea, JPQLQuery<EmployeeAuth> query) {
				if (entity.getEmployee() != null && entity.getEmployee().getUid() != null && entity.getEmployee().getUid().length() > 0) {
					query.where(qea.employee.uid.ne(entity.getEmployee().getUid()));
				}
			}
		});
	}

	private List<EmployeeAuth> tmpl(EmployeeAuth entity, WhereService<EmployeeAuth, QEmployeeAuth> where) {
		JPQLQuery<EmployeeAuth> query = this.getQuery(entity, where);

		if (entity.getPage() > 0) {
			long offset = (entity.getPage() - 1) * entity.getPageSize();
			query.offset(offset);
			query.limit(entity.getPageSize());
		}

		return query.fetchResults().getResults();
	}

	private Long tmplCount(EmployeeAuth entity, WhereService<EmployeeAuth, QEmployeeAuth> where) {
		JPQLQuery<EmployeeAuth> query = this.getQuery(entity, where);

		return query.fetchCount();
	}

	private JPQLQuery<EmployeeAuth> getQuery(EmployeeAuth entity, WhereService<EmployeeAuth, QEmployeeAuth> where) {
		QEmployeeAuth qea = QEmployeeAuth.employeeAuth;
		JPQLQuery<EmployeeAuth> query = from(qea);
		where.where(qea, query);

		if (entity.getId() != null && entity.getId().length() > 0) {
			query.where(qea.id.eq(entity.getId()));
		}

		query.orderBy(qea.lastdate.desc());
		return query;
	}
}
