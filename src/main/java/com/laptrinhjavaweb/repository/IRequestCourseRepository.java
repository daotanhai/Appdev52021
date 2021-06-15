package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.RequestCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRequestCourseRepository extends JpaRepository<RequestCourseEntity,Long> {
}
