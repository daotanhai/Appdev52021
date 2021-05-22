package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.TrainerCourseDTO;
import com.laptrinhjavaweb.entity.TrainerCourseEntity;
import org.springframework.stereotype.Component;

@Component
public class TrainerCourseConverter {
    public TrainerCourseDTO toDTO(TrainerCourseEntity trainerCourseEntity) {
        TrainerCourseDTO result = new TrainerCourseDTO();
        result.setId(trainerCourseEntity.getId());
        result.setCreatedBy(trainerCourseEntity.getCreatedBy());
        result.setCourseId(trainerCourseEntity.getCourseEntityForTrainer().getId());
        result.setTrainerId(trainerCourseEntity.getTrainerEntity().getId());
        return result;
    }
}
