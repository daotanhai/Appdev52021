package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {
    TrainerEntity findTrainerEntityByUserName(String userName);
}
