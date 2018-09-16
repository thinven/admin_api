package com.thinven.boot.domain.entity.commoncodeset.commoncode.dao;

import java.util.List;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.support.domain.entity.dao.ListDao;

public interface CommonCodeDao extends ListDao<CommonCode> {

	public List<CommonCode> listForCache(CommonCode commoncode);

}
