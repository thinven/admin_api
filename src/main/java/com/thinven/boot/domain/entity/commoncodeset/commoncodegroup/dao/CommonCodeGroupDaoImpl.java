package com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.CommonCodeGroup;
import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.repo.CommonCodeGroupRepository;
import com.thinven.boot.support.domain.entity.dao.EntityDao;

@Component("commonCodeGroupDao")
public class CommonCodeGroupDaoImpl extends EntityDao<CommonCodeGroup> implements CommonCodeGroupDao {

	@Autowired
	private CommonCodeGroupRepository commonCodeGroupRepository;

	@Override
	public List<CommonCodeGroup> list(CommonCodeGroup entity) {
		return this.commonCodeGroupRepository.list(entity);
	}

	@Override
	public CommonCodeGroup add(CommonCodeGroup entity) {
		entity.init();
		entity.setCache(10L);
		entity.setUse(10L);
		entity.setOrdered(1L);
		return this.commonCodeGroupRepository.save(entity);
	}

	@Override
	public CommonCodeGroup info(CommonCodeGroup entity) {
		return this.commonCodeGroupRepository.findById(entity.getUid()).orElse(null);
	}

}
