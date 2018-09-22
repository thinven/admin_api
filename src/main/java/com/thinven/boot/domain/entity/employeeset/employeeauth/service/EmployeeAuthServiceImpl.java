package com.thinven.boot.domain.entity.employeeset.employeeauth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.employeeset.employeeauth.EmployeeAuth;
import com.thinven.boot.domain.entity.employeeset.employeeauth.SecurityEmployee;
import com.thinven.boot.domain.entity.employeeset.employeeauth.dao.EmployeeAuthDao;
import com.thinven.boot.support.domain.entity.model.MemberModel;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;

@Service
@Transactional
public class EmployeeAuthServiceImpl extends BindService<EmployeeAuth> implements EmployeeAuthService {

	@Autowired
	private EmployeeAuthDao employeeAuthDao;

	@Override
	public UserDetails loadUserByUsername(String rk) throws UsernameNotFoundException {
		return Optional.ofNullable(employeeAuthDao.infoByRk(rk)).filter(ea -> ea != null).map(ea -> new SecurityEmployee(ea)).get();
	}

	@Override
	public Message<EmployeeAuth> list(Message<EmployeeAuth> msg) {
		if (msg.isOk()) {
			msg.add("employeeAuthlist", this.employeeAuthDao.list(msg.getParams()));
		}
		return msg;
	}

	@Override
	public MemberModel infoByRk(String rk) {
		return this.employeeAuthDao.infoByRk(rk);
	}

	@Override
	public Message<Employee> add(Message<Employee> msg, Employee info) {
		if (msg.isOk()) {
			this.employeeAuthDao.add(new EmployeeAuth(msg.getParams().getId(), info));
		}
		return msg;
	}

	@Override
	public Message<Employee> update(Message<Employee> msg, Employee employee) {
		if (msg.isOk()) {
			EmployeeAuth info = this.employeeAuthDao.infoByEmployee(employee);
			if (info != null) {
				info.setId(employee.getId());
			}
		}
		return msg;
	}

}
