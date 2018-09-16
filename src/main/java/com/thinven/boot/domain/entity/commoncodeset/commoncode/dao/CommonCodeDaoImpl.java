package com.thinven.boot.domain.entity.commoncodeset.commoncode.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.domain.entity.commoncodeset.commoncode.repo.CommonCodeRepository;
import com.thinven.boot.support.domain.entity.dao.EntityDao;

@Component("commoncodeDao")
public class CommonCodeDaoImpl extends EntityDao<CommonCode> implements CommonCodeDao {

	@Autowired
	private CommonCodeRepository commoncodeRepository;

	@Override
	public List<CommonCode> list(CommonCode entity) {
		return this.commoncodeRepository.findAll();
	}

	@Override
	public List<CommonCode> listForCache(CommonCode commoncode) {
		return this.commoncodeRepository.listForCache(commoncode);
	}

}
