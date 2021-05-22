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
        List<RoleEntity> entities = roleRepository.findAll();
        for (RoleEntity item : entities) {
            // vì mình k muốn tạo account cho role ADMIN, k hiển thị ra ngoài views có role
            // ADMIN nên cần né item có role ADMIN ra
            if (item.getCode().equalsIgnoreCase("ADMIN") == false) {
                result.put(item.getCode(), item.getName());
            }
        }
        return result;
    }

}
