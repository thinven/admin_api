package com.thinven.boot.domain.entity.deployment.service;

import com.thinven.boot.domain.entity.deployment.Deployment;
import com.thinven.boot.support.domain.entity.service.AddService;
import com.thinven.boot.support.domain.entity.service.InfoService;

public interface DeploymentService extends InfoService<Deployment>, AddService<Deployment> {

	String FILE_UPLOAD = "file_upload";
	String NEW_FOLDER = "new_folder";

}
