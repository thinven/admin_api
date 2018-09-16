package com.thinven.boot.domain.entity.employeeset.employeeauth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.thinven.boot.domain.entity.employeeset.employeerole.EmployeeRole;

public class SecurityEmployee extends User {

	private static final String ROLE_PREFIX = "ROLE_";
	private static final long serialVersionUID = 1L;

	public SecurityEmployee(EmployeeAuth employeeauth) {
		super(employeeauth.getId(), employeeauth.getPw(), makeGrantedAuthority(employeeauth.getRoles()));
	}

	private static List<GrantedAuthority> makeGrantedAuthority(List<EmployeeRole> roles) {
		List<GrantedAuthority> list = new ArrayList<>();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRole().getName())));
		return list;
	}
}
