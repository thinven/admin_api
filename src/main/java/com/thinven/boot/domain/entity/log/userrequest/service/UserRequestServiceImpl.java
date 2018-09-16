package com.thinven.boot.domain.entity.log.userrequest.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.log.userrequest.UserRequest;
import com.thinven.boot.domain.entity.log.userrequest.dao.UserRequestDao;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;

@Service
@Transactional
public class UserRequestServiceImpl<G> extends BindService<UserRequest<G>> implements UserRequestService<G> {

	@Autowired
	private UserRequestDao<G> userrequestDao;

	@Override
	public void add(Message<G> msg, UserRequest<G> entity, HttpServletRequest request) {
		this.userrequestDao.add(entity, request);
	}
}
