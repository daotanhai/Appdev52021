package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Map<String, String> findAll() {
        Map<String, String> result = new HashMap<String, String>();
        // Chỉ hiển thị ra cho ADMIN tạo 2 role là TRAINER vs TRAINING-STAFF
        RoleEntity roleEntity = roleRepository.findRoleEntitiesByCode("TRAINER");
        RoleEntity roleEntityTrainingStaff = roleRepository.findRoleEntitiesByCode("TRAINING-STAFF");
        result.put(roleEntity.getCode(), roleEntity.getName());
        result.put(roleEntityTrainingStaff.getCode(),roleEntityTrainingStaff.getName());
        return result;
    }

}
