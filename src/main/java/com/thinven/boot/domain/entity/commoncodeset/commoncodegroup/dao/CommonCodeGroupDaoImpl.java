package com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.CommonCodeGroup;
import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.repo.CommonCodeGroupRepository;
import com.thinven.boot.support.domain.entity.dao.EntityDao;

@Component("commoncodegroupDao")
public class CommonCodeGroupDaoImpl extends EntityDao<CommonCodeGroup> implements CommonCodeGroupDao {

	@Autowired
	private CommonCodeGroupRepository commoncodegroupRepository;

	@Override
	public List<CommonCodeGroup> list(CommonCodeGroup entity) {
		return this.commoncodegroupRepository.findAll();
	}

	@Override
	public CommonCodeGroup add(CommonCodeGroup commoncodegroup) {
		commoncodegroup.init();
		return this.commoncodegroupRepository.save(commoncodegroup);
	}

	@Override
	public CommonCodeGroup info(CommonCodeGroup commoncodegroup) {
		return this.commoncodegroupRepository.getOne(commoncodegroup.getUid());
	}

}
