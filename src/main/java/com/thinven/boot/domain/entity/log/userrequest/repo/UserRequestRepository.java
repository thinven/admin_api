package com.thinven.boot.domain.entity.log.userrequest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinven.boot.domain.entity.log.userrequest.UserRequest;

public interface UserRequestRepository<G> extends JpaRepository<UserRequest<G>, Long> {
}