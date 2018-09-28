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

	@Override
	public Message<CommonCode> update(Message<CommonCode> msg) {
		msg = this.commonCodeValidator.init(msg).required().result();

		if (msg.isOk()) {
			CommonCode info = this.commonCodeDao.info(msg.getParams());
			if (info != null) {
				CommonCodeGroup codeGroupInfo = this.commonCodeGroupService.info(msg.getParams());
				if (codeGroupInfo != null) {
					// 그룹이 바뀐경우 이전과 이후 그룹 모두 캐시를 클리어.
					if (!info.getCommonCodeGroup().getUid().equals(msg.getParams().getBcgu())) {
						this.commonCodeCacheService.clearCache(info.getCommonCodeGroup().getUid());
						msg.getParams().setCommonCodeGroup(codeGroupInfo);
						info.setCommonCodeGroup(codeGroupInfo);
					}
				} else {
					info.setCommonCodeGroup(this.commonCodeGroupService.add(msg.getParams()));
				}
				info.setCode(msg.getParams().getCode());
				info.setName(msg.getParams().getName());
				info.setOrdered(msg.getParams().getOrdered());
				info.setUse(msg.getParams().getUse());
				msg.add("commonCode", info);
				this.commonCodeCacheService.clearCache(info.getCommonCodeGroup().getUid());
			}
		}
		return msg;
	}

}
