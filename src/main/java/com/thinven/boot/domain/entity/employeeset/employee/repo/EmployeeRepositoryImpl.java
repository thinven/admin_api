package com.thinven.boot.domain.entity.employeeset.employee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employee.QEmployee;
import com.thinven.boot.support.domain.entity.repository.WhereService;
import com.thinven.boot.support.domain.entity.repository.WhereServiceImpl;

public class EmployeeRepositoryImpl extends QuerydslRepositorySupport implements EmployeeRepositoryA {

	public EmployeeRepositoryImpl() {
		super(Employee.class);
	}

	@Override
	public List<Employee> list(Employee employee) {
		return tmpl(employee, new WhereServiceImpl<Employee, QEmployee>(employee) {
			public void where(QEmployee qe, JPQLQuery<Employee> query) {

			}
		});
	}

	@Override
	public Long count(Employee employee) {
		return tmplCount(employee, new WhereServiceImpl<Employee, QEmployee>(employee) {
			public void where(QEmployee qe, JPQLQuery<Employee> query) {

			}
		});
	}

	private List<Employee> tmpl(Employee entity, WhereService<Employee, QEmployee> where) {
		JPQLQuery<Employee> query = this.getQuery(entity, where);

		if (entity.getPage() > 0) {
			long offset = (entity.getPage() - 1) * entity.getPageSize();
			query.offset(offset);
			query.limit(entity.getPageSize());
		}

		return query.fetchResults().getResults();
	}

	private Long tmplCount(Employee entity, WhereService<Employee, QEmployee> where) {
		JPQLQuery<Employee> query = this.getQuery(entity, where);

		return query.fetchCount();
	}

	private JPQLQuery<Employee> getQuery(Employee entity, WhereService<Employee, QEmployee> where) {
		QEmployee qe = QEmployee.employee;
		JPQLQuery<Employee> query = from(qe);
		where.where(qe, query);

		query.where(qe.delete.eq(20L));

		if (entity.getFirstname() != null && entity.getFirstname().length() > 0) {
			query.where(qe.firstname.startsWith(entity.getFirstname()));
		}
		if (entity.getLastname() != null && entity.getLastname().length() > 0) {
			query.where(qe.lastname.startsWith(entity.getLastname()));
		}
		if (entity.getBirthday() != null && entity.getBirthday().length() > 0) {
			query.where(qe.birthday.startsWith(entity.getBirthday()));
		}
		if (entity.getGender() != null && entity.getGender().longValue() > 0) {
			query.where(qe.gender.eq(entity.getGender()));
		}
		if (entity.getPhone() != null && entity.getPhone().length() > 0) {
			query.where(qe.phone.startsWith(entity.getPhone()));
		}
		if (entity.getEmail() != null && entity.getEmail().length() > 0) {
			query.where(qe.email.startsWith(entity.getEmail()));
		}

		query.orderBy(qe.lastdate.desc());
		return query;
	}
}
