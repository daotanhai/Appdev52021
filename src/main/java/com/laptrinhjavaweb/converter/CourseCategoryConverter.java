package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CourseCategoryDTO;
import com.laptrinhjavaweb.entity.CourseCategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseCategoryConverter {
    // convert CourseCategoryEntity to CourseCategoryDTO
    public CourseCategoryDTO toDTO(CourseCategoryEntity courseCategoryEntity) {
        CourseCategoryDTO result = new CourseCategoryDTO();
        // Lấy ra id của loại khóa học
        result.setId(courseCategoryEntity.getId());
        result.setCreatedBy(courseCategoryEntity.getCreatedBy());
        // Tên loại khóa học
        result.setNameCourseCategory(courseCategoryEntity.getNameCourseCategory());
        // Name code của loại khóa học
        result.setCourseCategoryNameCode(courseCategoryEntity.getCourseCategoryNameCode());
        // Mô tả ngắn về loại khóa học
        result.setCourseCategoryDescription(courseCategoryEntity.getCourseCategoryDescription());
        return result;
    }

    // convert CourseCategoryDTO to CourseCategoryEntity
    public CourseCategoryEntity toEntity(CourseCategoryDTO courseCategoryDTO) {
        CourseCategoryEntity result = new CourseCategoryEntity();
        result.setNameCourseCategory(courseCategoryDTO.getNameCourseCategory());
        result.setCourseCategoryNameCode(courseCategoryDTO.getCourseCategoryNameCode());
        result.setCourseCategoryDescription(courseCategoryDTO.getCourseCategoryDescription());
        return result;
    }

    // converter để update course category từ DTO -> Entity
    public CourseCategoryEntity toEntity(CourseCategoryEntity result, CourseCategoryDTO courseCategoryDTO) {
        result.setNameCourseCategory(courseCategoryDTO.getNameCourseCategory());
        result.setCourseCategoryNameCode(courseCategoryDTO.getCourseCategoryNameCode());
        result.setCourseCategoryDescription(courseCategoryDTO.getCourseCategoryDescription());
        return result;
    }
}
