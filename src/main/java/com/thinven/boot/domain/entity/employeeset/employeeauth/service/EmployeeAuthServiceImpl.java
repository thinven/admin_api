package com.thinven.boot.domain.entity.employeeset.employeeauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employeeauth.EmployeeAuth;
import com.thinven.boot.domain.entity.employeeset.employeeauth.dao.EmployeeAuthDao;
import com.thinven.boot.support.domain.entity.model.MemberModel;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.model.SecurityModel;
import com.thinven.boot.support.domain.entity.service.BindService;
import com.thinven.boot.support.security.RsaUtil;
import com.thinven.boot.support.util.DateUtil;
import com.thinven.boot.support.util.ParamUtil;

@Service
@Transactional
public class EmployeeAuthServiceImpl extends BindService<EmployeeAuth> implements EmployeeAuthService {

	@Autowired
	private EmployeeAuthDao employeeAuthDao;

	@Autowired
	private EmployeeAuthCacheService employeeAuthCacheService;

	@Override
	public Message<EmployeeAuth> info(Message<EmployeeAuth> msg) {
		if (msg.isOk()) {
			EmployeeAuth info = this.employeeAuthDao.infoByIdAndPw(msg.getParams());
			if (info != null) {
				this.setAuth(msg, info);
				this.employeeAuthCacheService.clearCache(msg.getParams().getRk());
			} else
				msg.setMsg("WAR_MSG_NOT_EQUALS", "EMPLOYEEAUTH");
		}
		return msg;
	}

	@Override
	public MemberModel infoByRk(String rk) {
		return this.employeeAuthDao.infoByRk(rk);
	}

	@Override
	public Message<Employee> add(Message<Employee> msg, Employee employeeInfo) {
		if (msg.isOk()) {
			EmployeeAuth info = this.employeeAuthDao.add(new EmployeeAuth(msg.getParams().getId(), employeeInfo));
			employeeInfo.setEmployeeAuth(info);
		}
		return msg;
	}

	@Override
	public Message<Employee> update(Message<Employee> msg, Employee employee) {
		if (msg.isOk()) {
			EmployeeAuth info = this.employeeAuthDao.infoByEmployee(employee);
			if (info != null) {
				info.setId(employee.getId());
				this.employeeAuthCacheService.clearCache(info.getRk());
			}
		}
		return msg;
	}

	private void setAuth(Message<EmployeeAuth> msg, EmployeeAuth info) {
		info.setRk(this.newRk());
		info.setLastdate(DateUtil.now());
		msg.add("rk", info.getRk());
		msg.add("id", info.getId());
		msg.add("firstname", info.getEmployee().getFirstname());
		msg.add("lastname", info.getEmployee().getLastname());

		SecurityModel model = RsaUtil.generateKey();
		info.setPk(model.getPrivateKey());
		msg.add("pkm2", model.getPkm());
		msg.add("pke2", model.getPke());
	}

	private String newRk() {
		return ParamUtil.getRandomKey(8121, 32);
	}
}
