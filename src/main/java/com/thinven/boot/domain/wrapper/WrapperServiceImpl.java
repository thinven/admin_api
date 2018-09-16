package com.thinven.boot.domain.wrapper;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinven.boot.domain.entity.employeeset.employeeauth.service.EmployeeAuthService;
import com.thinven.boot.domain.entity.log.userrequest.UserRequest;
import com.thinven.boot.domain.entity.log.userrequest.service.UserRequestService;
import com.thinven.boot.domain.entity.log.waserror.WasError;
import com.thinven.boot.domain.entity.log.waserror.service.WasErrorService;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.AddService;
import com.thinven.boot.support.domain.entity.service.BindService;
import com.thinven.boot.support.domain.entity.service.CheckService;
import com.thinven.boot.support.domain.entity.service.InfoService;
import com.thinven.boot.support.domain.entity.service.ListService;
import com.thinven.boot.support.domain.entity.service.SearchService;
import com.thinven.boot.support.domain.entity.service.UpdateService;
import com.thinven.boot.support.log.Log;

@Service
public class WrapperServiceImpl<G> extends BindService<G> implements WrapperService<G> {

	@Autowired
	private WasErrorService<G> wasErrorService;

	@Autowired
	private UserRequestService<G> userRequestService;

	@Autowired
	private EmployeeAuthService employeeAuthService;

	@Override
	public Message<G> list(ListService<G> listService, G entity, HttpServletRequest request) {
		Message<G> msg = new Message<G>();
		try {
			msg.setParams(entity);
			msg = this.bindP1(msg, employeeAuthService);
			msg = listService.list(msg);
			this.userrequest(msg, entity, request);
		} catch (Exception ex) {
			msg.setMsg("ERR_EXCEPTION", "PARAM_ERROR");
			Log.error(this, "WrapperServiceImpl.list() : " + ex.getMessage());
			this.waserror(msg, entity, ex, request);
		}
		return msg;
	}

	@Override
	public Message<G> search(SearchService<G> listService, G entity, HttpServletRequest request) {
		Message<G> msg = new Message<G>();
		try {
			msg.setParams(entity);
			msg = this.bindP1(msg, employeeAuthService);
			msg = listService.search(msg);
			this.userrequest(msg, entity, request);
		} catch (Exception ex) {
			msg.setMsg("ERR_EXCEPTION", "PARAM_ERROR");
			Log.error(this, "WrapperServiceImpl.search() : " + ex.getMessage());
			this.waserror(msg, entity, ex, request);
		}
		return msg;
	}

	@Override
	public Message<G> add(AddService<G> addService, G entity, HttpServletRequest request) {
		Message<G> msg = new Message<G>();
		try {
			msg.setParams(entity);
			msg = this.bindP1(msg, employeeAuthService);
			msg = addService.add(msg);
			this.userrequest(msg, entity, request);
		} catch (Exception ex) {
			msg.setMsg("ERR_EXCEPTION", "PARAM_ERROR");
			Log.error(this, "WrapperServiceImpl.add() : " + ex.getMessage());
			this.waserror(msg, entity, ex, request);
		}
		return msg;
	}

	@Override
	public Message<G> info(InfoService<G> infoService, G entity, HttpServletRequest request) {
		Message<G> msg = new Message<G>();
		try {
			msg.setParams(entity);
			msg = this.bindP1(msg, employeeAuthService);
			msg = infoService.info(msg);
			this.userrequest(msg, entity, request);
		} catch (Exception ex) {
			msg.setMsg("ERR_EXCEPTION", "PARAM_ERROR");
			Log.error(this, "WrapperServiceImpl.info() : " + ex.getMessage());
			this.waserror(msg, entity, ex, request);
		}
		return msg;
	}

	@Override
	public Message<G> update(UpdateService<G> updateService, G entity, HttpServletRequest request) {
		Message<G> msg = new Message<G>();
		try {
			msg.setParams(entity);
			msg = this.bindP1(msg, employeeAuthService);
			msg = updateService.update(msg);
			this.userrequest(msg, entity, request);
		} catch (Exception ex) {
			msg.setMsg("ERR_EXCEPTION", "PARAM_ERROR");
			Log.error(this, "WrapperServiceImpl.update() : " + ex.getMessage());
			this.waserror(msg, entity, ex, request);
		}
		return msg;
	}

	@Override
	public Message<G> check(CheckService<G> checkService, G entity, HttpServletRequest request) {
		Message<G> msg = new Message<G>();
		try {
			msg.setParams(entity);
			msg = this.bindP1(msg, employeeAuthService);
			msg = checkService.check(msg);
			this.userrequest(msg, entity, request);
		} catch (Exception ex) {
			msg.setMsg("ERR_EXCEPTION", "PARAM_ERROR");
			Log.error(this, "WrapperServiceImpl.check() : " + ex.getMessage());
			this.waserror(msg, entity, ex, request);
		}
		return msg;
	}

	private void userrequest(Message<G> msg, G entity, HttpServletRequest request)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (request.getServerName().contains(WrapperService.WHITE_SERVER)) {
			this.userRequestService.add(msg, new UserRequest<G>(entity), request);
		}
	}

	private void waserror(Message<G> msg, G entity, Exception ex, HttpServletRequest request) {
		this.wasErrorService.add(msg, new WasError<G>(entity, ex), request);
	}
}
