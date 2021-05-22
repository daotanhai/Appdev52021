package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.CourseCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseCategoryRepository extends JpaRepository<CourseCategoryEntity, Long>, JpaSpecificationExecutor<CourseCategoryEntity> {
    CourseCategoryEntity findByCourseCategoryNameCode(String courseCategoryNameCode);

    CourseCategoryEntity findCourseCategoryEntityByCourseCategoryNameCode(String courseCategoryNameCode);

    CourseCategoryEntity findCourseCategoryEntityByNameCourseCategory(String nameCourseCategory);

    CourseCategoryEntity findCourseCategoryEntityByCourseCategoryDescription(String courseCategoryDescription);
}
