package com.thinven.boot.support.domain.entity.service;

import com.thinven.boot.support.domain.entity.model.Message;

public interface CheckService<G> {

	public Message<G> check(Message<G> msg);
}
