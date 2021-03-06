package com.thinven.boot.domain.entity.employeeset.employeeauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.thinven.boot.domain.entity.employeeset.employeeauth.EmployeeAuth;
import com.thinven.boot.domain.entity.employeeset.employeeauth.dao.EmployeeAuthDao;

@Service
@EnableCaching
public class EmployeeAuthCacheServiceImpl implements EmployeeAuthCacheService {
	@Autowired
	private EmployeeAuthDao employeeAuthDao;

	@Override
	@Cacheable(cacheNames = "employeeAuthCache", key = "#rk")
	public String infoByRkForAop(String rk) {
		EmployeeAuth info = this.employeeAuthDao.infoByRk(rk);
		if (info != null) {
			return info.getEmployee().getRolejson();
		} else {
			return "";
		}
	}

	@Override
	@CacheEvict(cacheNames = "employeeAuthCache", key = "#rk")
	public void clearCache(String rk) {
	}

}
