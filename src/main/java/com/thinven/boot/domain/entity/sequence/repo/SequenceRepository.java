package com.thinven.boot.domain.entity.sequence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinven.boot.domain.entity.sequence.Sequence;

public interface SequenceRepository extends JpaRepository<Sequence, String> {

	Sequence findByName(String name);

}
