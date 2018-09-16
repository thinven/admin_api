package com.thinven.boot.domain.entity.log.waserror.service;

import javax.servlet.http.HttpServletRequest;

import com.thinven.boot.domain.entity.log.waserror.WasError;
import com.thinven.boot.support.domain.entity.model.Message;

public interface WasErrorService<G> {

	void add(Message<G> msg, WasError<G> waserror, HttpServletRequest request);

}
