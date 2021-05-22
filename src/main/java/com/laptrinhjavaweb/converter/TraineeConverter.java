package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.TraineeDTO;
import com.laptrinhjavaweb.entity.TraineeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TraineeConverter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // convert TraineeEntity to TraineeDTO
    public TraineeDTO toDTO(TraineeEntity traineeEntity) {
        TraineeDTO result = new TraineeDTO();
        result.setId(traineeEntity.getId());
        result.setUserName(traineeEntity.getUserName());
        result.setPassword(traineeEntity.getPassword());
        result.setdOB(traineeEntity.getdOB());
        result.setAge(traineeEntity.getAge());
        result.setDepartment(traineeEntity.getDepartment());
        result.setEducation(traineeEntity.getEducation());
        result.setExperienceDetails(traineeEntity.getExperienceDetails());
        result.setLocation(traineeEntity.getLocation());
        result.setMainProgrammingLangugage(traineeEntity.getMainProgrammingLangugage());
        result.setName(traineeEntity.getName());
        result.setToeicScore(traineeEntity.getToeicScore());
        // get role code tu entity set qua cho DTO
        result.setRoleCode(traineeEntity.getRoleTrainee().getCode());
        return result;
    }

    // convert TraineeDTO to TraineeEntity
    public TraineeEntity toEntity(TraineeDTO traineeDTO) {
        TraineeEntity result = new TraineeEntity();
        result.setAge(traineeDTO.getAge());
        result.setUserName(traineeDTO.getUserName());
        result.setdOB(traineeDTO.getdOB());
        result.setDepartment(traineeDTO.getDepartment());
        result.setEducation(traineeDTO.getEducation());
        result.setExperienceDetails(traineeDTO.getExperienceDetails());
        result.setLocation(traineeDTO.getLocation());
        result.setMainProgrammingLangugage(traineeDTO.getMainProgrammingLangugage());
        result.setName(traineeDTO.getName());
        result.setToeicScore(traineeDTO.getToeicScore());
        // role set trong service sau
        return result;
    }

    public TraineeEntity toEntity(TraineeEntity result, TraineeDTO traineeDTO) {
        result.setAge(traineeDTO.getAge());
        result.setdOB(traineeDTO.getdOB());
        result.setUserName(traineeDTO.getUserName());
        result.setDepartment(traineeDTO.getDepartment());
        result.setEducation(traineeDTO.getEducation());
        result.setExperienceDetails(traineeDTO.getExperienceDetails());
        result.setLocation(traineeDTO.getLocation());
        result.setMainProgrammingLangugage(traineeDTO.getMainProgrammingLangugage());
        result.setName(traineeDTO.getName());
        result.setToeicScore(traineeDTO.getToeicScore());
        return result;
    }
}
