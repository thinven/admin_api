package com.thinven.boot.domain.entity.log.userrequest.service;

import javax.servlet.http.HttpServletRequest;

import com.thinven.boot.domain.entity.log.userrequest.UserRequest;
import com.thinven.boot.support.domain.entity.model.Message;

public interface UserRequestService<G> {

	void add(Message<G> msg, UserRequest<G> userrequest, HttpServletRequest request);

}
