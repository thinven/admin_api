package com.thinven.boot.domain.entity.employeeset.employeerole.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.thinven.boot.domain.entity.employeeset.employeerole.EmployeeRole;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, String>, JpaSpecificationExecutor<EmployeeRole>, EmployeeRoleRepositoryA {

}