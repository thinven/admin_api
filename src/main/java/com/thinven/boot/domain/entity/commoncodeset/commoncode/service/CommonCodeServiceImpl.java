package com.thinven.boot.domain.entity.commoncodeset.commoncode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.domain.entity.commoncodeset.commoncode.dao.CommonCodeDao;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;

@Service
@Transactional
public class CommonCodeServiceImpl extends BindService<CommonCode> implements CommonCodeService {

	@Autowired
	private CommonCodeDao commoncodeDao;

	@Override
	public Message<CommonCode> list(Message<CommonCode> msg) {
		// msg = this.bindP1(msg, this.memberService);

		if (msg.isOk()) {
			msg.add("commoncodelist", this.commoncodeDao.list(msg.getParams()));
		}
		return msg;
	}

	@Override
	public List<CommonCode> listForCache(CommonCode commoncode) {
		commoncode.setPage(0);
		return this.commoncodeDao.listForCache(commoncode);
	}

}
