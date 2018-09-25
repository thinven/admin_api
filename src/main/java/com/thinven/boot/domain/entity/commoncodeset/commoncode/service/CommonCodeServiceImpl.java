package com.thinven.boot.domain.entity.commoncodeset.commoncode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;
import com.thinven.boot.domain.entity.commoncodeset.commoncode.dao.CommonCodeDao;
import com.thinven.boot.domain.entity.commoncodeset.commoncode.validator.CommonCodeValidator;
import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.CommonCodeGroup;
import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.service.CommonCodeGroupService;
import com.thinven.boot.support.constant.Codes;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;

@Service
@Transactional
public class CommonCodeServiceImpl extends BindService<CommonCode> implements CommonCodeService {

	@Autowired
	private CommonCodeDao commonCodeDao;
	@Autowired
	private CommonCodeValidator commonCodeValidator;

	@Autowired
	private CommonCodeCacheService commonCodeCacheService;

	@Autowired
	private CommonCodeGroupService commonCodeGroupService;

	@Override
	public Message<CommonCode> list(Message<CommonCode> msg) {
		if (msg.isOk()) {
			msg.add("commonCodeList", this.commonCodeDao.list(msg.getParams()));
			msg.add("useCodes", this.commonCodeCacheService.codes(Codes.USE));
		}
		return msg;
	}

	@Override
	public Message<CommonCode> add(Message<CommonCode> msg) {
		msg = this.commonCodeValidator.init(msg).required().result();

		if (msg.isOk()) {
			CommonCodeGroup codeGroupInfo = this.commonCodeGroupService.info(msg.getParams());
			if (codeGroupInfo != null) {
				msg.getParams().setCommonCodeGroup(codeGroupInfo);
			} else {
				msg.getParams().setCommonCodeGroup(this.commonCodeGroupService.add(msg.getParams()));
			}
			msg.add("commonCode", this.commonCodeDao.add(msg.getParams()));
			this.commonCodeCacheService.clearCache(msg.getParams().getCommonCodeGroup().getUid());
		}
		return msg;
	}

}
