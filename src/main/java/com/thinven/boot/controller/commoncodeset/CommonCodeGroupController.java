package com.thinven.boot.controller.commoncodeset;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = "/api/commonCodeGroups")
@Api(tags = "공통코드그룹")
public class CommonCodeGroupController {

	@Autowired
	private WrapperService<CommonCodeGroup> wrapperService;

	@Autowired
	private CommonCodeGroupService commonCodeGroupService;

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "공통코드그룹 목록 조회", notes = "사이트내에서 사용하는 공통코드그룹 목록을 조회하는 API.")
	public ModelAndView list(String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		CommonCodeGroup commonCodeGroup = new CommonCodeGroup(p1, p2, p3);
		return this.wrapperService.list(this.commonCodeGroupService, commonCodeGroup, request).toMAV();
	}

	@RequestMapping(method = RequestMethod.PATCH)
	@ApiOperation(value = "공통코드그룹 수정", notes = "사이트내에서 사용하는 공통코드그룹을 수정하는 API.")
	public ModelAndView update(@RequestBody Map<String, Object> payload, HttpServletRequest request) throws IOException {
		CommonCodeGroup commonCodeGroup = new CommonCodeGroup(payload);
		return this.wrapperService.update(this.commonCodeGroupService, commonCodeGroup, request).toMAV();
	}
}
