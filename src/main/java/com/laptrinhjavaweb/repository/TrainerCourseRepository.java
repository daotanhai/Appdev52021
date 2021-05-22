package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.TrainerCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerCourseRepository extends JpaRepository<TrainerCourseEntity, Long> {
    List<TrainerCourseEntity> findTrainerCourseEntitiesByTrainerEntity_Id(long id);
}
