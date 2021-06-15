package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.repository.IRoleRepository;
import com.laptrinhjavaweb.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository IRoleRepository;

    @Override
    public Map<String, String> findAll() {
        Map<String, String> result = new HashMap<>();
        // Chỉ hiển thị ra cho ADMIN tạo user vs 2 role là TRAINER vs TRAINING-STAFF
        RoleEntity roleEntity = IRoleRepository.findRoleEntitiesByCode("TRAINER");
        RoleEntity roleEntityTrainingStaff = IRoleRepository.findRoleEntitiesByCode("TRAINING-STAFF");
        result.put(roleEntity.getCode(), roleEntity.getName());
        result.put(roleEntityTrainingStaff.getCode(),roleEntityTrainingStaff.getName());
        return result;
    }

}
