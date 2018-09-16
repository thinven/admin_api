package com.thinven.boot.domain.entity.log.rkhistory.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.log.rkhistory.RKHistory;
import com.thinven.boot.domain.entity.log.rkhistory.repo.RKHistoryRepository;
import com.thinven.boot.support.domain.entity.dao.EntityDao;
import com.thinven.boot.support.util.DateUtil;

@Component("rkhistoryDao")
public class RKHistoryDaoImpl extends EntityDao<RKHistory> implements RKHistoryDao {

	@Autowired
	private RKHistoryRepository rkhistoryRepository;

	@Override
	public RKHistory add(RKHistory rkhistory) {
		rkhistory.setWorkdate(DateUtil.getDashDate());
		rkhistory.setLast();
		return this.rkhistoryRepository.save(rkhistory);
	}

}
