package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.TrainingStaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITrainingStaffRepository extends JpaRepository<TrainingStaffEntity, Long> {
    TrainingStaffEntity findTrainingStaffEntityByUserName(String userName);
}
