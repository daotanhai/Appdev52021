package com.laptrinhjavaweb.initialDatabase;

import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.TraineeEntity;
import com.laptrinhjavaweb.entity.TrainerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Service
public class InitialDatabase {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

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
        roleRepository.save(trainingStaff);
        roleRepository.save(trainer);
        roleRepository.save(trainee);
    }
    public void createUser(){
        UserEntity userEntity = new UserEntity();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setCode("ADMIN");
        roleEntity.setName("quan tri");
        roleRepository.save(roleEntity);

        userEntity.setUserName("admin");
        userEntity.setPassword("$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG");
        userEntity.setFullName("dao tan hai");
        userEntity.setStatus(1);
        userEntity.setRoleEntity(roleEntity);
        createRole();
        userRepository.save(userEntity);
    }

}
