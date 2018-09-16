package com.thinven.boot.domain.entity.log.waserror.dao;

import javax.servlet.http.HttpServletRequest;

import com.thinven.boot.domain.entity.log.waserror.WasError;

public interface WasErrorDao<G> {

	WasError<G> add(WasError<G> waserror, HttpServletRequest request);

}
