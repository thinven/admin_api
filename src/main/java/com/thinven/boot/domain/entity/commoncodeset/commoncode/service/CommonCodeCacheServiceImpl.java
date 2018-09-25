package com.thinven.boot.domain.entity.commoncodeset.commoncode.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.domain.entity.commoncodeset.commoncode.dao.CommonCodeDao;

@Service
@EnableCaching
public class CommonCodeCacheServiceImpl implements CommonCodeCacheService {
	@Autowired
	private CommonCodeDao commonCodeDao;

	@Override
	@Cacheable(cacheNames = "commonCodeCache", key = "#bcgu")
	public List<Map<String, String>> codes(String bcgu) {
		List<CommonCode> list = this.commonCodeDao.codes(bcgu);
		List<Map<String, String>> codeset = new ArrayList<>();
		for (CommonCode info : list) {
			Map<String, String> map = new HashMap<>();
			map.put("code", info.getCode() + "");
			map.put("name", info.getName());
			map.put("ordered", info.getOrdered() + "");
			codeset.add(map);
		}
		return codeset;
	}

	@Override
	@CacheEvict(cacheNames = "commonCodeCache", key = "#bcgu")
	public void clearCache(String bcgu) {
	}

}
