package com.thinven.boot.support.domain.entity.repository;

import com.querydsl.jpa.JPQLQuery;

public class WhereServiceImpl<E, Q> implements WhereService<E, Q> {

	private E entity;

	public WhereServiceImpl() {

	}

	public WhereServiceImpl(E entity) {
		this.entity = entity;
	}

	@Override
	public void where(Q q, JPQLQuery<E> query) {

	}

	// Get & Set
	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

}
