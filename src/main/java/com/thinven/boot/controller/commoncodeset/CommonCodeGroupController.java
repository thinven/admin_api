package com.thinven.boot.controller.commoncodeset;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.CommonCodeGroup;
import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.service.CommonCodeGroupService;
import com.thinven.boot.domain.wrapper.WrapperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/commoncodegroups")
@Api(tags = "공통코드그룹")
public class CommonCodeGroupController {

	@Autowired
	private WrapperService<CommonCodeGroup> wrapperService;

	@Autowired
	private CommonCodeGroupService commoncodegroupService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "공통코드그룹 목록 조회", notes = "사이트내에서 사용하는 공통코드그룹 목록을 조회하는 API.")
	public ModelAndView list(String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		CommonCodeGroup commoncodegroup = new CommonCodeGroup(p1, p2, p3);
		return this.wrapperService.list(this.commoncodegroupService, commoncodegroup, request).toMAV();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiOperation(value = "공통코드그룹 등록", notes = "사이트내에서 사용하는 공통코드그룹를 등록하는 API.")
	public ModelAndView add(String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		CommonCodeGroup commoncodegroup = new CommonCodeGroup(p1, p2, p3);
		return this.wrapperService.add(this.commoncodegroupService, commoncodegroup, request).toMAV();
	}

	@RequestMapping(value = "/{uid}", method = RequestMethod.GET)
	@ApiOperation(value = "공통코드그룹 조회", notes = "사이트내에서 사용하는 공통코드그룹을 조회하는 API.")
	public ModelAndView info(@PathVariable String uid, String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		CommonCodeGroup commoncodegroup = new CommonCodeGroup(p1, p2, p3);
		return this.wrapperService.info(this.commoncodegroupService, commoncodegroup, request).toMAV();
	}

}
