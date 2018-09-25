package com.thinven.boot.controller.commoncodeset;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.domain.entity.commoncodeset.commoncode.service.CommonCodeService;
import com.thinven.boot.domain.wrapper.WrapperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/commonCodes")
@Api(tags = "공통코드")
public class CommonCodeController {

	@Autowired
	private WrapperService<CommonCode> wrapperService;

	@Autowired
	private CommonCodeService commonCodeService;

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "공통코드 목록 조회", notes = "사이트내에서 사용하는 공통코드 목록을 조회하는 API.")
	public ModelAndView list(String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		CommonCode commonCode = new CommonCode(p1, p2, p3);
		return this.wrapperService.list(this.commonCodeService, commonCode, request).toMAV();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "공통코드 등록", notes = "사이트내에서 사용하는 공통코드를 등록하는 API.")
	public ModelAndView add(String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		CommonCode commonCode = new CommonCode(p1, p2, p3);
		return this.wrapperService.add(this.commonCodeService, commonCode, request).toMAV();
	}
}
