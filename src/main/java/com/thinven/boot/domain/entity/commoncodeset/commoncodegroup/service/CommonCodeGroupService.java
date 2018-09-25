package com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.service;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.CommonCodeGroup;
import com.thinven.boot.support.domain.entity.service.ListService;

public interface CommonCodeGroupService extends ListService<CommonCodeGroup> {

	CommonCodeGroup info(CommonCode commonCode);

	CommonCodeGroup add(CommonCode commonCode);

}
