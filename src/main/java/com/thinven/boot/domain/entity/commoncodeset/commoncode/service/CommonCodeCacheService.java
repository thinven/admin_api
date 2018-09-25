package com.thinven.boot.domain.entity.commoncodeset.commoncode.service;

import java.util.List;
import java.util.Map;

public interface CommonCodeCacheService {
	List<Map<String, String>> codes(String bcgu);

	void clearCache(String uid);
}
