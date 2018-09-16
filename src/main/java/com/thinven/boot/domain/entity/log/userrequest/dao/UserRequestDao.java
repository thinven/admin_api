package com.thinven.boot.domain.entity.log.userrequest.dao;

import javax.servlet.http.HttpServletRequest;

import com.thinven.boot.domain.entity.log.userrequest.UserRequest;

public interface UserRequestDao<G> {

	UserRequest<G> add(UserRequest<G> userrequest, HttpServletRequest request);

}
