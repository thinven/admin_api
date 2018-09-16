package com.thinven.boot.domain.entity.log.waserror.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinven.boot.domain.entity.log.waserror.WasError;

public interface WasErrorRepository<G> extends JpaRepository<WasError<G>, Long> {
}