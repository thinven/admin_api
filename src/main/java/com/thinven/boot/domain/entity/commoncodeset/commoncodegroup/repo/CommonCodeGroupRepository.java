package com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.CommonCodeGroup;

public interface CommonCodeGroupRepository
		extends JpaRepository<CommonCodeGroup, String>, JpaSpecificationExecutor<CommonCodeGroup>, CommonCodeGroupRepositoryA {

}
