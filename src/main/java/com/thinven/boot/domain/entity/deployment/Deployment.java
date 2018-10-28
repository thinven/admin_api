package com.thinven.boot.domain.entity.deployment;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.thinven.boot.support.domain.entity.model.FileEntityModel;

public class Deployment extends FileEntityModel {

	public Deployment() {
		super();
	}

	public Deployment(String p1, String p2, String p3) {
		this();
		this.setP1(p1);
		this.setP2(p2);
		this.setP3(p3);
	}

	public Deployment(String p1, String p2, String p3, List<MultipartFile> files) {
		this();
		this.setP1(p1);
		this.setP2(p2);
		this.setP3(p3);
		this.setFiles(files);
	}

	@Override
	public long getOwnerseq() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setOwnerseq(long ownerseq) {
		// TODO Auto-generated method stub

	}

}
