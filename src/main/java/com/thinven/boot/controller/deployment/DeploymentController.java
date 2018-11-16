package com.thinven.boot.controller.deployment;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = "/api/deployment")
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
		deployment.setWorkspace(DeploymentService.GET_FILELIST);
		return this.wrapperService.info(this.deploymentService, deployment, request).toMAV();
	}

	@RequestMapping(value = "/text", method = RequestMethod.GET)
	@ApiOperation(value = "배포폴더 단일 파일내용 조회", notes = "배포폴더 단일 파일내용 조회하는 API.")
	public ModelAndView text(String p1, String p2, String p3, HttpServletRequest request) throws IOException {
		Deployment deployment = new Deployment(p1, p2, p3);
		deployment.setWorkspace(DeploymentService.GET_FILETEXT);
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

	@RequestMapping(value="/text", method = RequestMethod.PATCH)
	@ApiOperation(value = "단일 파일 내용 저장", notes = "배포폴더에 단일파일 내용을 수정하는 API.")
	public ModelAndView update(@RequestBody Map<String, Object> payload, HttpServletRequest request) throws IOException {
		Deployment deployment = new Deployment(payload);
		return this.wrapperService.update(this.deploymentService, deployment, request).toMAV();
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@ApiOperation(value = "배포폴더에 파일 삭제", notes = "배포폴더에 폴더를 삭제하는 API.")
	public ModelAndView delete(@RequestBody Map<String, Object> payload, HttpServletRequest request) throws IOException {
		Deployment deployment = new Deployment(payload);
		return this.wrapperService.delete(this.deploymentService, deployment, request).toMAV();
	}

}
