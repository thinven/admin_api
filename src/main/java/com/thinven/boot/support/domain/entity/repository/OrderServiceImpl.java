package com.thinven.boot.support.domain.entity.repository;

import com.querydsl.jpa.JPQLQuery;

public class OrderServiceImpl<E, Q> implements OrderService<E, Q> {

	private E entity;

	public OrderServiceImpl() {

	}

	public OrderServiceImpl(E entity) {
		this.entity = entity;
	}

	@Override
	public void order(Q q, JPQLQuery<E> query) {

	}

	// Get & Set
	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

}
