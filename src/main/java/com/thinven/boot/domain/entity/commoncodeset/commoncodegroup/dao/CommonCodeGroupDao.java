package com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.dao;

import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.CommonCodeGroup;
import com.thinven.boot.support.domain.entity.dao.AddDao;
import com.thinven.boot.support.domain.entity.dao.InfoDao;
import com.thinven.boot.support.domain.entity.dao.ListDao;

public interface CommonCodeGroupDao extends ListDao<CommonCodeGroup>, AddDao<CommonCodeGroup>, InfoDao<CommonCodeGroup> {

}
