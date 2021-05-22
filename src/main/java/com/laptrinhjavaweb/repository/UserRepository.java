package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByUserNameAndStatus(String userName, int status);

    UserEntity findUserEntityByUserName(String userName);
    // Tra ve 1 cai gi do -> FindOne
    // by username va status
    // no du dong tim password

}
