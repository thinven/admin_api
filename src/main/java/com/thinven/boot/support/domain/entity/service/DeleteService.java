package com.thinven.boot.support.domain.entity.service;

import com.thinven.boot.support.domain.entity.model.Message;

public interface DeleteService<G> {

	public Message<G> delete(Message<G> msg);

}
