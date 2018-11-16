package com.thinven.boot.support.aspect;

public class UrlRoles {

	private String url;
	private String[] roles;

	public UrlRoles() {
	}

	public UrlRoles(String url, String role) {
		this();
		this.url = url;
		this.roles = new String[] { role };
	}

	// Get & Set
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

}
