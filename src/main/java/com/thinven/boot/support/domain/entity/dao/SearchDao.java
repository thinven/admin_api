package com.thinven.boot.support.domain.entity.dao;

import java.util.List;

public interface SearchDao<G> {

	public List<G> search(G entity);
}
