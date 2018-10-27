package com.thinven.boot.controller.deployment;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.thinven.boot.domain.entity.deployment.Deployment;
import com.thinven.boot.domain.entity.deployment.service.DeploymentService;
import com.thinven.boot.domain.wrapper.WrapperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/deployment")
@Api(tags = "배포 관리")
public class DeploymentController {

	@Autowired
	private WrapperService<Deployment> wrapperService;

	@Autowired
	private DeploymentService deploymentService;

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "배포 주소 조회", notes = "배포 주소 조회하는 API.")
	public ModelAndView info(String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		Deployment deployment = new Deployment(p1, p2, p3);
		return this.wrapperService.info(this.deploymentService, deployment, request).toMAV();
	}

}
