package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findOneByCode(String code);
    RoleEntity findRoleEntitiesByCode(String code);
}
