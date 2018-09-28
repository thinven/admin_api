package com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.service;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.CommonCodeGroup;
import com.thinven.boot.support.domain.entity.service.ListService;
import com.thinven.boot.support.domain.entity.service.UpdateService;

public interface CommonCodeGroupService extends ListService<CommonCodeGroup>, UpdateService<CommonCodeGroup> {

	CommonCodeGroup info(CommonCode commonCode);

	CommonCodeGroup add(CommonCode commonCode);

}
