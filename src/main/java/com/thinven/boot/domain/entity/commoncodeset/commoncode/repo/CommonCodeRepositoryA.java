package com.thinven.boot.domain.entity.commoncodeset.commoncode.repo;

import java.util.List;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.support.domain.entity.repository.ListRepository;

public interface CommonCodeRepositoryA extends ListRepository<CommonCode> {

	public List<CommonCode> listForCache(CommonCode commoncode);

}
