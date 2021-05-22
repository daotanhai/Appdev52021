package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserForAdminService {
    List<UserDTO> findAll(Pageable pageable);

    UserDTO save(UserDTO userDTO);

    void saveTrainerRoleAndUserName(UserDTO userDTO);

    void saveTrainingStaffRoleAndUserNameAndPassword(UserDTO userDTO);

    UserDTO findById(long id);

    void delete(long[] ids);

    int getTotalItem();
}
