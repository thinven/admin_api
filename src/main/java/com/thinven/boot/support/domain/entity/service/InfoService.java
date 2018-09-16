package com.thinven.boot.support.domain.entity.service;

import com.thinven.boot.support.domain.entity.model.Message;

public interface InfoService<G> {

	public Message<G> info(Message<G> msg);

}
