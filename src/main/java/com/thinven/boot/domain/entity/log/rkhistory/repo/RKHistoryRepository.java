package com.thinven.boot.domain.entity.log.rkhistory.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinven.boot.domain.entity.log.rkhistory.RKHistory;

public interface RKHistoryRepository extends JpaRepository<RKHistory, Long> {
}