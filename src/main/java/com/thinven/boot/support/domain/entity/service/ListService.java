package com.thinven.boot.support.domain.entity.service;

import com.thinven.boot.support.domain.entity.model.Message;

public interface ListService<G> {

	public Message<G> list(Message<G> msg);
}
