package com.thinven.boot.domain.entity.commoncodeset.commoncode.dao;

import java.util.List;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.support.domain.entity.dao.AddDao;
import com.thinven.boot.support.domain.entity.dao.InfoDao;
import com.thinven.boot.support.domain.entity.dao.ListDao;

public interface CommonCodeDao extends ListDao<CommonCode>, InfoDao<CommonCode>, AddDao<CommonCode> {

	public List<CommonCode> codes(String bcgu);

}
