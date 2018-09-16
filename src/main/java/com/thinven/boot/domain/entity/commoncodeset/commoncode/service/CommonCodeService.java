package com.thinven.boot.domain.entity.commoncodeset.commoncode.service;

import java.util.List;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.support.domain.entity.service.ListService;

public interface CommonCodeService extends ListService<CommonCode> {

	public static final long YES = 1;
	public static final long NO = 0;

	public List<CommonCode> listForCache(CommonCode commoncode);

}
