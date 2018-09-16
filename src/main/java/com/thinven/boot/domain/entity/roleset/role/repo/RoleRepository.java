package com.thinven.boot.domain.entity.roleset.role.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.thinven.boot.domain.entity.roleset.role.Role;

public interface RoleRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role>, RoleRepositoryA {

}