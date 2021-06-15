package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICourseRepository extends JpaRepository<CourseEntity, Long> {
    CourseEntity findByCourseName(String courseName);

    List<CourseEntity> findCourseEntitiesByCourseName(String courseName);

    List<CourseEntity> findCourseEntitiesByCourseCategory_CourseCategoryNameCode(String courseCategoryNameCode);

    List<CourseEntity> findCourseEntitiesByShortDescription(String shortDescription);

    List<CourseEntity> findCourseEntitiesByCourseNameAndCourseCategory_CourseCategoryNameCode(String courseName, String courseCategoryNameCode);

    List<CourseEntity> findCourseEntitiesByCourseCategory_CourseCategoryNameCodeAndShortDescription(String courseCategoryNameCode, String shortDescription);

    List<CourseEntity> findCourseEntitiesByCourseNameAndShortDescription(String courseName, String shortDescription);

    long countCourseEntitiesByCourseCategory_CourseCategoryNameCode(String courseCategoryNameCode);

}
