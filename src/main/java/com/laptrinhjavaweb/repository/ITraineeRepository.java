package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.TraineeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITraineeRepository extends JpaRepository<TraineeEntity, Long> {
    List<TraineeEntity> findTraineeEntityByToeicScore(String toeicScore);

    TraineeEntity findTraineeEntityByUserName(String userName);

    List<TraineeEntity> findTraineeEntityByMainProgrammingLangugage(String mainProgrammingLangugage);

    List<TraineeEntity> findTraineeEntitiesByToeicScoreAndMainProgrammingLangugage(String toeicScore, String mainProgrammingLangugage);
}
