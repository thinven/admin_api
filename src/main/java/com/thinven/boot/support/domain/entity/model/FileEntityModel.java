package com.thinven.boot.support.domain.entity.model;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class FileEntityModel extends VOTypeEntityModel {

	@Transient
	public static final long FILE_LIMIT = 7168000;

	private String originname;
	private String phypath;
	private String mimetype;
	private long size;
	//
	@Transient
	private List<MultipartFile> files;

	@JsonIgnore
	public String getExt() {
		int pos = this.originname.lastIndexOf(".");
		if (pos > -1) {
			if (this.originname.substring(pos).length() > 3) {
				return this.originname.substring(pos, pos + 4);
			} else {
				return ".jpg";
			}
		} else {
			return ".jpg";
		}
	}

	@JsonIgnore
	public String getOnlyPath() {
		int pos = this.phypath.lastIndexOf("/");
		if (pos > -1) {
			return this.phypath.substring(0, pos);
		} else {
			return "";
		}
	}

	public abstract long getOwnerseq();

	public abstract void setOwnerseq(long ownerseq);

	// Get & Set
	public String getOriginname() {
		return originname;
	}

	public void setOriginname(String originname) {
		this.originname = originname;
	}

	public String getPhypath() {
		return phypath;
	}

	public void setPhypath(String phypath) {
		this.phypath = phypath;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@JsonIgnore
	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

}
