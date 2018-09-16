package com.thinven.boot.domain.entity.log.waserror.dao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.log.waserror.WasError;
import com.thinven.boot.domain.entity.log.waserror.repo.WasErrorRepository;
import com.thinven.boot.support.domain.entity.dao.EntityDao;
import com.thinven.boot.support.util.DateUtil;

@Component("waserrorDao")
public class WasErrorDaoImpl<G> extends EntityDao<WasError<G>> implements WasErrorDao<G> {

	@Autowired
	private WasErrorRepository<G> waserrorRepository;

	@Override
	public WasError<G> add(WasError<G> waserror, HttpServletRequest request) {
		waserror.setWorkdate(DateUtil.getDashDate());
		waserror.setRequesturi(request.getRequestURL().toString());
		waserror.setLast();
		return this.waserrorRepository.save(waserror);
	}

}
