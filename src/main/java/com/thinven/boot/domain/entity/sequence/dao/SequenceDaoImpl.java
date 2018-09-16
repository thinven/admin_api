package com.thinven.boot.domain.entity.sequence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinven.boot.domain.entity.sequence.Sequence;
import com.thinven.boot.domain.entity.sequence.repo.SequenceRepository;
import com.thinven.boot.support.domain.entity.dao.EntityDao;

@Component("sequenceDao")
public class SequenceDaoImpl extends EntityDao<Sequence> implements SequenceDao {

	@Autowired
	private SequenceRepository sequenceRepository;

	@Override
	public Sequence add(Sequence entity) {
		return this.sequenceRepository.save(entity);
	}

	@Override
	public Sequence info(Sequence entity) {
		return this.sequenceRepository.findByName(entity.getName());
	}

}
