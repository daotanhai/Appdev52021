package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.TrainerCourseDTO;
import com.laptrinhjavaweb.dto.TrainerDTO;
import com.laptrinhjavaweb.dto.TrainerForTrainingStaffDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ITrainerService {
    List<TrainerDTO> findAll(Pageable pageable);

    TrainerDTO findById(Long id);

    TrainerDTO saveTrainerForTrainer(TrainerDTO trainerDTO);

    TrainerForTrainingStaffDTO saveTrainerForTrainingStaff(TrainerForTrainingStaffDTO trainerForTrainingStaffDTO);
    int getTotalTrainers();

    Map<String, String> findAll();

    void deleteTrainer(long[] ids);

    void saveCourseAssign(long[] ids);

    List<TrainerCourseDTO> findCourseOnTrainerId(long id);

    void deleteTrainerCourse(long[] ids);
}
