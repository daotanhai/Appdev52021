package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.TrainingStaffDTO;
import com.laptrinhjavaweb.entity.TrainingStaffEntity;
import org.springframework.stereotype.Component;

@Component
public class TrainingStaffConverter {
    // entity to DTO
    public TrainingStaffDTO toDTO(TrainingStaffEntity trainingStaffEntity) {
        TrainingStaffDTO trainingStaffDTO = new TrainingStaffDTO();
        trainingStaffDTO.setName(trainingStaffEntity.getName());
        trainingStaffDTO.setWorkingPlace(trainingStaffEntity.getWorkingPlace());
        trainingStaffDTO.setTelephone(trainingStaffEntity.getTelephone());
        trainingStaffDTO.setEmail(trainingStaffEntity.getEmail());
        // get role from table ROLE -> return CODE ( TRAINING-STAFF)
        trainingStaffDTO.setRole(trainingStaffEntity.getRoleTrainingStaff().getCode());
        return trainingStaffDTO;
    }

    // DTO to Entity
    public TrainingStaffEntity toEntity(TrainingStaffDTO trainingStaffDTO) {
        TrainingStaffEntity trainingStaffEntity = new TrainingStaffEntity();
        trainingStaffEntity.setName(trainingStaffDTO.getName());
        trainingStaffEntity.setWorkingPlace(trainingStaffDTO.getWorkingPlace());
        trainingStaffEntity.setTelephone(trainingStaffDTO.getTelephone());
        trainingStaffEntity.setEmail(trainingStaffDTO.getEmail());
        return trainingStaffEntity;
    }
}
