package com.thinven.boot.support.domain.entity.repository;

import com.querydsl.jpa.JPQLQuery;

public interface OrderService<E, Q> {
	public void order(Q q, JPQLQuery<E> query);
}
