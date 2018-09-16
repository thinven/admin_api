package com.thinven.boot.domain.entity.sequence.dao;

import com.thinven.boot.domain.entity.sequence.Sequence;
import com.thinven.boot.support.domain.entity.dao.AddDao;
import com.thinven.boot.support.domain.entity.dao.InfoDao;

public interface SequenceDao extends AddDao<Sequence>, InfoDao<Sequence> {

}
