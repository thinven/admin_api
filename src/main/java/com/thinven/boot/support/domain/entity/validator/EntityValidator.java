package com.thinven.boot.support.domain.entity.validator;

import com.thinven.boot.support.domain.entity.model.Message;

public interface EntityValidator<G> extends BaseValidator {

	public Message<G> validate(Message<G> msg);

}
