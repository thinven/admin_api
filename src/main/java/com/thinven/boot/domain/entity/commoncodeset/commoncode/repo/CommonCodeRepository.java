package com.thinven.boot.domain.entity.commoncodeset.commoncode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.thinven.boot.domain.entity.commoncodeset.commoncode.CommonCode;

public interface CommonCodeRepository extends JpaRepository<CommonCode, String>, JpaSpecificationExecutor<CommonCode>, CommonCodeRepositoryA {

}