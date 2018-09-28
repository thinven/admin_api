package com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.CommonCodeGroup;
import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.dao.CommonCodeGroupDao;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;

@Service
@Transactional
public class CommonCodeGroupServiceImpl extends BindService<CommonCodeGroup> implements CommonCodeGroupService {

	@Autowired
	private CommonCodeGroupDao commonCodeGroupDao;

	@Override
	public Message<CommonCodeGroup> list(Message<CommonCodeGroup> msg) {
		if (msg.isOk()) {
			msg.add("commonCodeGroupList", this.commonCodeGroupDao.list(msg.getParams()));
		}
		return msg;
	}

	@Override
	public CommonCodeGroup info(CommonCode commonCode) {
		return this.commonCodeGroupDao.info(new CommonCodeGroup(commonCode.getBcgu()));
	}

	@Override
	public CommonCodeGroup add(CommonCode commonCode) {
		CommonCodeGroup params = new CommonCodeGroup();
		params.setUid(commonCode.getBcgu());
		params.setName(commonCode.getBcgn());
		return this.commonCodeGroupDao.add(params);
	}

	@Override
	public Message<CommonCodeGroup> update(Message<CommonCodeGroup> msg) {
		if (msg.isOk()) {
			CommonCodeGroup info = this.commonCodeGroupDao.info(msg.getParams());
			if (info != null) {
				info.setName(msg.getParams().getName());
				info.setUse(msg.getParams().getUse());
				msg.add("commonCodeGroup", info);
			}
		}
		return msg;
	}

}
