package com.thinven.boot.support.domain.entity.repository;

import com.querydsl.jpa.JPQLQuery;

public interface WhereService<E, Q> {
	public void where(Q q, JPQLQuery<E> query);
}
