package com.thinven.boot.support.domain.entity.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class PageModel extends SecurityModel {

	@Transient
	private String qs;
	@Transient
	private long page;
	@Transient
	private long pageSize;

	public PageModel() {
		this.page = 1;
		this.pageSize = 10;
	}

	public long getPages(long count) {
		long pages = 0;
		if (count % this.pageSize > 0) {
			pages = count / this.pageSize + 1;
		} else {
			pages = count / this.pageSize;
		}
		return pages;
	}

	// Get & Set
	@JsonIgnore
	public String getQs() {
		return qs;
	}

	public void setQs(String qs) {
		this.qs = qs;
	}

	@JsonIgnore
	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	@JsonIgnore
	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

}
