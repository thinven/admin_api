package com.thinven.boot.support.domain.entity.service;

import com.thinven.boot.support.domain.entity.model.Message;

public interface UpdateService<G> {

	public Message<G> update(Message<G> msg);
}
