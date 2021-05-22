package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.TraineeCourseDTO;
import com.laptrinhjavaweb.entity.TraineeCourseEntity;
import org.springframework.stereotype.Component;

@Component
public class TraineeCourseConverter {
    // convert TraineeCourseEntity to TraineeCourseDTO
    public TraineeCourseDTO toDTO(TraineeCourseEntity traineeCourseEntity) {
        TraineeCourseDTO result = new TraineeCourseDTO();
        result.setId(traineeCourseEntity.getId());
        result.setCreatedBy(traineeCourseEntity.getCreatedBy());
        result.setCourseId(traineeCourseEntity.getCourseEntityForTrainee().getId());
        result.setTraineeId(traineeCourseEntity.getTraineeEntity().getId());
        return result;
    }
}
