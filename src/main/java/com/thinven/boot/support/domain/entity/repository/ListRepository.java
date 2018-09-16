package com.thinven.boot.support.domain.entity.repository;

import java.util.List;

public interface ListRepository<G> {

	public List<G> list(G entity);
}
