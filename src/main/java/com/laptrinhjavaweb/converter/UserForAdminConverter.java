package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserForAdminConverter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // convert UserEntity to UserDTO
    public UserDTO toDTO(UserEntity userEntity) {
        UserDTO result = new UserDTO();
        result.setId(userEntity.getId());
        result.setUserName(userEntity.getUserName());
        result.setPassword(userEntity.getPassword());
        // 1 - n moi get code duoc, n - n chua biet? entity -> dto thi lam duoc,
        // dto->entity chua support
        result.setRole(userEntity.getRoleEntity().getCode());
        return result;
    }

    // convert UserDTO to UserEntity
    // role lam sau trong service, gui role gi ve dung role do
    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity result = new UserEntity();
        result.setUserName(userDTO.getUserName());
        // Ma hoa MD5 password
        result.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        result.setStatus(userDTO.getStatus());
        return result;
    }

    // Có converter này để dùng trong service
    public UserEntity toEntity(UserEntity result, UserDTO userDTO) {
        result.setUserName(userDTO.getUserName());
        result.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        result.setStatus(userDTO.getStatus());
        return result;
    }

}
