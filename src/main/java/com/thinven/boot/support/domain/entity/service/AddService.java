package com.thinven.boot.support.domain.entity.service;

import com.thinven.boot.support.domain.entity.model.Message;

public interface AddService<G> {

	public Message<G> add(Message<G> msg);
}
