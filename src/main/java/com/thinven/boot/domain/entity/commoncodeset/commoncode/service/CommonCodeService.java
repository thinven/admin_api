package com.thinven.boot.domain.entity.commoncodeset.commoncode.service;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.support.domain.entity.service.AddService;
import com.thinven.boot.support.domain.entity.service.ListService;
import com.thinven.boot.support.domain.entity.service.UpdateService;

public interface CommonCodeService extends ListService<CommonCode>, AddService<CommonCode>, UpdateService<CommonCode> {

}
