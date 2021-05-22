package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserForAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "userAPIOfAdmin")
public class TrainerAPI {
    @Autowired
    private IUserForAdminService iUserForAdminService;

    // RequestBody la cai nhan vao tu client ( contentType)
    // dataType la cai gui ve client
    // parse tu json -> DTO
    @PostMapping("/api/user")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getRole().equalsIgnoreCase("TRAINER")) {
            iUserForAdminService.saveTrainerRoleAndUserName(userDTO);
        } else if (userDTO.getRole().equalsIgnoreCase("TRAINING-STAFF")) {
            iUserForAdminService.saveTrainingStaffRoleAndUserNameAndPassword(userDTO);
        }
        return iUserForAdminService.save(userDTO);
    }

    @DeleteMapping("/api/user")
    public void deleteUser(@RequestBody long[] ids) {
        iUserForAdminService.delete(ids);
    }

    @PutMapping("/api/user")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return iUserForAdminService.save(userDTO);
    }
}
