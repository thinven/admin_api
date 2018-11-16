package com.thinven.boot.controller.roleset;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.thinven.boot.domain.entity.roleset.role.Role;
import com.thinven.boot.domain.entity.roleset.role.service.RoleService;
import com.thinven.boot.domain.wrapper.WrapperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/roles")
@Api(tags = "역할")
public class RoleController {

	@Autowired
	private WrapperService<Role> wrapperService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "역할 목록 조회", notes = "사이트내에서 사용하는 역할 목록을 조회하는 API.")
	public ModelAndView list(String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		Role role = new Role(p1, p2, p3);
		return this.wrapperService.list(this.roleService, role, request).toMAV();
	}

}
