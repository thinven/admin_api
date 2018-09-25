package com.thinven.boot.domain.entity.commoncodeset.commoncode.service;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.support.domain.entity.service.AddService;
import com.thinven.boot.support.domain.entity.service.ListService;

public interface CommonCodeService extends ListService<CommonCode>, AddService<CommonCode> {

	public static final long YES = 1;
	public static final long NO = 0;

}
