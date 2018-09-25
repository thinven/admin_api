package com.thinven.boot.domain.entity.commoncodeset.commoncode.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.domain.entity.commoncodeset.commoncode.repo.CommonCodeRepository;
import com.thinven.boot.support.domain.entity.dao.EntityDao;

@Component("commonCodeDao")
public class CommonCodeDaoImpl extends EntityDao<CommonCode> implements CommonCodeDao {

	@Autowired
	private CommonCodeRepository commonCodeRepository;

	@Override
	public List<CommonCode> list(CommonCode entity) {
		return this.commonCodeRepository.list(entity);
	}

	@Override
	public List<CommonCode> codes(String bcgu) {
		return this.commonCodeRepository.codes(bcgu);
	}

	@Override
	public CommonCode add(CommonCode entity) {
		entity.init();
		return this.commonCodeRepository.save(entity);
	}

}
