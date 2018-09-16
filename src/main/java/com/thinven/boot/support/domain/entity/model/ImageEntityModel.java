package com.thinven.boot.support.domain.entity.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class ImageEntityModel extends FileEntityModel {

	@Transient
	public static final long IMAGE_LIMIT = 7168000;

	private long width;
	private long height;

	// Get & Set
	public long getWidth() {
		return width;
	}

	public void setWidth(long width) {
		this.width = width;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

}
