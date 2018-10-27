package com.thinven.boot.domain.entity.deployment;

import com.thinven.boot.support.domain.entity.model.EntityModel;

public class Deployment extends EntityModel {

	public Deployment() {
		super();
	}

	public Deployment(String p1, String p2, String p3) {
		this();
		this.setP1(p1);
		this.setP2(p2);
		this.setP3(p3);
	}

	@Override
	public void setUid(String uid) {

	}

}
