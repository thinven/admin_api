package com.thinven.boot.domain.entity.log.rkhistory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.log.rkhistory.RKHistory;
import com.thinven.boot.domain.entity.log.rkhistory.dao.RKHistoryDao;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;

@Service
@Transactional
public class RKHistoryServiceImpl extends BindService<RKHistory> implements RKHistoryService {

	@Autowired
	private RKHistoryDao rkhistoryDao;

	@Override
	public Message<RKHistory> add(Message<RKHistory> msg) {
		if (msg.isOk()) {
			this.rkhistoryDao.add(msg.getParams());
		}
		return msg;
	}

}
