package com.thinven.boot.domain.entity.log.waserror.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.log.waserror.WasError;
import com.thinven.boot.domain.entity.log.waserror.dao.WasErrorDao;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;

@Service
@Transactional
public class WasErrorServiceImpl<G> extends BindService<WasError<G>> implements WasErrorService<G> {

	@Autowired
	private WasErrorDao<G> waserrorDao;

	@Override
	public void add(Message<G> msg, WasError<G> waserror, HttpServletRequest request) {
		if (waserror.getSimplemsg().length() > 300) {
			waserror.setSimplemsg(waserror.getSimplemsg().substring(0, 300));
		}
		this.waserrorDao.add(waserror, request);
	}

}
