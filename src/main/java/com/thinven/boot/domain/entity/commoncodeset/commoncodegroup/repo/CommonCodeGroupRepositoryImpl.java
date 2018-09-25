package com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.CommonCodeGroup;
import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.QCommonCodeGroup;
import com.thinven.boot.support.domain.entity.repository.WhereService;
import com.thinven.boot.support.domain.entity.repository.WhereServiceImpl;

public class CommonCodeGroupRepositoryImpl extends QuerydslRepositorySupport implements CommonCodeGroupRepositoryA {

	public CommonCodeGroupRepositoryImpl() {
		super(CommonCodeGroup.class);
	}

	@Override
	public List<CommonCodeGroup> list(CommonCodeGroup entity) {
		return tmpl(entity, new WhereServiceImpl<CommonCodeGroup, QCommonCodeGroup>(entity) {
			public void where(QCommonCodeGroup qcg, JPQLQuery<CommonCodeGroup> query) {
				
			}
		});
	}

	private List<CommonCodeGroup> tmpl(CommonCodeGroup entity, WhereService<CommonCodeGroup, QCommonCodeGroup> where) {
		QCommonCodeGroup qcg = QCommonCodeGroup.commonCodeGroup;

		JPQLQuery<CommonCodeGroup> query = from(qcg);

		where.where(qcg, query);

		if (entity.getName() != null && entity.getName().length() > 0) {
			query.where(qcg.name.contains(entity.getName()));
		}

		query.orderBy(qcg.ordered.asc());

		if (entity.getPage() > 0) {
			long offset = (entity.getPage() - 1) * entity.getPageSize();
			query.offset(offset);
			query.limit(entity.getPageSize());
		}

		return query.fetchResults().getResults();
	}
}
