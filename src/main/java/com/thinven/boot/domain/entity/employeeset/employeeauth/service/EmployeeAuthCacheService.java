package com.thinven.boot.domain.entity.employeeset.employeeauth.service;

public interface EmployeeAuthCacheService {
	public String infoByRkForAop(String rk);

	void clearCache(String rk);
}
