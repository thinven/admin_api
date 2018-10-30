package com.thinven.boot.controller.deployment;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
	@ApiOperation(value = "배포폴더 파일목록 조회", notes = "배포폴더 파일목록 조회하는 API.")
	public ModelAndView info(String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		Deployment deployment = new Deployment(p1, p2, p3);
		return this.wrapperService.info(this.deploymentService, deployment, request).toMAV();
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ApiOperation(value = "배포폴더에 파일업로드", notes = "배포폴더에 파일 업로드하는 API.")
	public ModelAndView upload(String p1, String p2, String p3, List<MultipartFile> files, HttpServletRequest request) throws IOException {
		Deployment deployment = new Deployment(p1, p2, p3, files);
		deployment.setWorkspace(DeploymentService.FILE_UPLOAD);
		return this.wrapperService.add(this.deploymentService, deployment, request).toMAV();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "배포폴더에 폴더 생성", notes = "배포폴더에 폴더를 생성하는 API.")
	public ModelAndView add(String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		Deployment deployment = new Deployment(p1, p2, p3);
		deployment.setWorkspace(DeploymentService.NEW_FOLDER);
		return this.wrapperService.add(this.deploymentService, deployment, request).toMAV();
	}

}
