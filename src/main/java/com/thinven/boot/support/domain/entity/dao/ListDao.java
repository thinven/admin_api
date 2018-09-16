package com.thinven.boot.support.domain.entity.dao;

import java.util.List;

public interface ListDao<G> {

	public List<G> list(G entity);
}
