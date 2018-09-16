package com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.CommonCodeGroup;
import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.dao.CommonCodeGroupDao;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;

@Service
@Transactional
public class CommonCodeGroupServiceImpl extends BindService<CommonCodeGroup> implements CommonCodeGroupService {

	@Autowired
	private CommonCodeGroupDao commoncodegroupDao;

	@Override
	public Message<CommonCodeGroup> list(Message<CommonCodeGroup> msg) {
		if (msg.isOk()) {
			msg.add("list", this.commoncodegroupDao.list(msg.getParams()));
		}
		return msg;
	}

	@Override
	public Message<CommonCodeGroup> add(Message<CommonCodeGroup> msg) {
		if (msg.isOk()) {
			this.commoncodegroupDao.add(msg.getParams());
		}
		return msg;
	}

	@Override
	public Message<CommonCodeGroup> info(Message<CommonCodeGroup> msg) {
		if (msg.isOk()) {
			this.commoncodegroupDao.info(msg.getParams());
		}
		return msg;
	}

}
