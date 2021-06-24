package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IUserForAdminService {
    List<UserDTO> findAll(Pageable pageable);

    UserDTO saveUser(UserDTO userDTO);

    void saveTrainerRoleAndUserName(UserDTO userDTO);

    void saveTrainingStaffRoleAndUserNameAndPassword(UserDTO userDTO);

    UserDTO findById(long id);

    void delete(long[] ids);

    int getTotalItem();

    /*void generateOneTimePassword(UserEntity userEntity) throws UnsupportedEncodingException, MessagingException;

    void sendOTPEmail(UserEntity userEntity, String OTP) throws UnsupportedEncodingException, MessagingException;

    void clearOTP(UserEntity userEntity);*/
}
