package com.laptrinhjavaweb.initialDatabase;

import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IRoleRepository;
import com.laptrinhjavaweb.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitialDatabase {
    @Autowired
    IUserRepository IUserRepository;

    @Autowired
    IRoleRepository IRoleRepository;

    // Create role trainer vs training staff vs trainee
    public void createRole(){
        RoleEntity trainingStaff = new RoleEntity();
        RoleEntity trainer = new RoleEntity();
        RoleEntity trainee = new RoleEntity();
        trainingStaff.setCode("TRAINING-STAFF");
        trainingStaff.setName("training staff");

        trainer.setCode("TRAINER");
        trainer.setName("trainer");

        trainee.setCode("TRAINEE");
        trainee.setName("trainee");
        IRoleRepository.save(trainingStaff);
        IRoleRepository.save(trainer);
        IRoleRepository.save(trainee);
    }
    public void createUser(){
        UserEntity userEntity = new UserEntity();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setCode("ADMIN");
        roleEntity.setName("quan tri");
        IRoleRepository.save(roleEntity);

        userEntity.setUserName("admin");
        userEntity.setPassword("$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG");
        userEntity.setFullName("dao tan hai");
        userEntity.setStatus(1);
        userEntity.setRoleEntity(roleEntity);
        createRole();
        IUserRepository.save(userEntity);
    }

}
