package com.thinven.boot.support.domain.entity.service;

import com.thinven.boot.support.domain.entity.model.Message;

public interface SearchService<G> {

	public Message<G> search(Message<G> msg);
}
