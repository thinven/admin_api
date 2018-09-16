package com.thinven.boot.domain.entity.sequence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.sequence.Sequence;
import com.thinven.boot.domain.entity.sequence.dao.SequenceDao;
import com.thinven.boot.support.domain.entity.model.Message;

@Service
@Transactional
public class SequenceServiceImpl implements SequenceService {

	@Autowired
	private SequenceDao sequenceDao;

	@Override
	public Message<Sequence> info(Message<Sequence> msg) {
		if (msg.isOk()) {
			msg.setInfo(this.sequenceDao.info(msg.getParams()));
		}
		return msg;
	}

}
