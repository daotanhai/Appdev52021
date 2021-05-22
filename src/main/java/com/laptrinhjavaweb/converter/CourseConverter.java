package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CourseDTO;
import com.laptrinhjavaweb.entity.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {
    // convert CourseEntity -> CourseDTO
    public CourseDTO toDTO(CourseEntity courseEntity) {
        CourseDTO result = new CourseDTO();
        result.setId(courseEntity.getId());
        result.setCreatedBy(courseEntity.getCreatedBy());
        result.setCourseName(courseEntity.getCourseName());
        result.setShortDescription(courseEntity.getShortDescription());
        result.setCourseCategoryId(courseEntity.getCourseCategory().getId());
        result.setCourseCategoryNameCode(courseEntity.getCourseCategory().getCourseCategoryNameCode());
        return result;
    }

    // convert CourseDTO -> CourseEntity
    public CourseEntity toEntity(CourseDTO courseDTO) {
        CourseEntity result = new CourseEntity();
        result.setCourseName(courseDTO.getCourseName());
        result.setShortDescription(courseDTO.getShortDescription());
        // Thể loại set trong service sau
        return result;
    }

    // convert CourseDTO cũ thành mới - 2 params
    public CourseEntity toEntity(CourseEntity result, CourseDTO courseDTO) {
        result.setCourseName(courseDTO.getCourseName());
        result.setShortDescription(courseDTO.getShortDescription());
        return result;
    }
}
