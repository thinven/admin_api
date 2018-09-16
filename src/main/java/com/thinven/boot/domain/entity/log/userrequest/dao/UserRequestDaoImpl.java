package com.thinven.boot.domain.entity.log.userrequest.dao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.log.userrequest.UserRequest;
import com.thinven.boot.domain.entity.log.userrequest.repo.UserRequestRepository;
import com.thinven.boot.support.domain.entity.dao.EntityDao;
import com.thinven.boot.support.util.DateUtil;

@Component("userrequestDao")
public class UserRequestDaoImpl<G> extends EntityDao<UserRequest<G>> implements UserRequestDao<G> {

	@Autowired
	private UserRequestRepository<G> userRequestRepository;

	@Override
	public UserRequest<G> add(UserRequest<G> userrequest, HttpServletRequest request) {
		userrequest.setWorkdate(DateUtil.getDashDate());
		userrequest.setRequesturi(request.getRequestURL().toString());
		userrequest.setLast();
		return this.userRequestRepository.save(userrequest);
	}

}
