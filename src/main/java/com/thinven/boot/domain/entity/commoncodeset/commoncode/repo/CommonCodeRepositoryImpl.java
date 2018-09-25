package com.thinven.boot.domain.entity.commoncodeset.commoncode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.domain.entity.commoncodeset.commoncode.QCommonCode;
import com.thinven.boot.domain.entity.commoncodeset.commoncode.service.CommonCodeService;
import com.thinven.boot.support.domain.entity.repository.WhereService;
import com.thinven.boot.support.domain.entity.repository.WhereServiceImpl;

public class CommonCodeRepositoryImpl extends QuerydslRepositorySupport implements CommonCodeRepositoryA {

	public CommonCodeRepositoryImpl() {
		super(CommonCode.class);
	}

	@Override
	public List<CommonCode> list(CommonCode commoncode) {
		return tmpl(commoncode, new WhereServiceImpl<CommonCode, QCommonCode>(commoncode) {
			public void where(QCommonCode qcc, JPQLQuery<CommonCode> query) {
				
			}
		});
	}

	@Override
	public List<CommonCode> listForCache(CommonCode commoncode) {
		return tmpl(commoncode, new WhereServiceImpl<CommonCode, QCommonCode>(commoncode) {
			public void where(QCommonCode qcc, JPQLQuery<CommonCode> query) {
				query.where(qcc.commonCodeGroup.cache.eq(CommonCodeService.YES));
				query.where(qcc.commonCodeGroup.use.eq(CommonCodeService.YES));
				query.where(qcc.use.eq(CommonCodeService.YES));
			}
		});
	}

	private List<CommonCode> tmpl(CommonCode entity, WhereService<CommonCode, QCommonCode> where) {
		QCommonCode qcc = QCommonCode.commonCode;

		JPQLQuery<CommonCode> query = from(qcc);

		where.where(qcc, query);

		if (entity.getCommonCodeGroup()!=null && entity.getCommonCodeGroup().getName() != null && entity.getCommonCodeGroup().getName().length() > 0) {
			query.where(qcc.commonCodeGroup.name.startsWith(entity.getCommonCodeGroup().getName()));
		}

		query.orderBy(qcc.commonCodeGroup.name.asc());
		query.orderBy(qcc.ordered.asc());

		if (entity.getPage() > 0) {
			long offset = (entity.getPage() - 1) * entity.getPageSize();
			query.offset(offset);
			query.limit(entity.getPageSize());
		}

		return query.fetchResults().getResults();
	}
}
