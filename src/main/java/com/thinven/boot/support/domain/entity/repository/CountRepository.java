package com.thinven.boot.support.domain.entity.repository;

public interface CountRepository<G> {

	public Long count(G entity);
}
