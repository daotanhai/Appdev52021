package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.RequestCourseDTO;
import com.laptrinhjavaweb.entity.RequestCourseEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class RequestCourseConverter {
    // course id va trainee id set o service
    // convert Entity to DTO
    public RequestCourseDTO toDTO(RequestCourseEntity requestCourseEntity){
        RequestCourseDTO results = new RequestCourseDTO();
        results.setId(requestCourseEntity.getId());
        results.setCreatedDate((Timestamp) requestCourseEntity.getCreatedDate());
        results.setCreatedBy(requestCourseEntity.getCreatedBy());
        return results;
    }
    // convert DTO to Entity
    public RequestCourseEntity toEntity(RequestCourseDTO requestCourseDTO){
        RequestCourseEntity results = new RequestCourseEntity();
        results.setCreatedDate(requestCourseDTO.getCreatedDate());
        results.setCreatedBy(requestCourseDTO.getCreatedBy());
        return results;
    }
}
