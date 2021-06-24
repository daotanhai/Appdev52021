package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.TraineeCourseDTO;
import com.laptrinhjavaweb.dto.TraineeDTO;
import com.laptrinhjavaweb.entity.TraineeEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITraineeService {
    // saveUser trainee entity
    void saveTraineeEntity(TraineeEntity traineeEntity);

    // Find ALL trainee
    List<TraineeDTO> findAll();

    // Hien thi ALL trainee - LIST
    List<TraineeDTO> findAll(Pageable pageable);

    // Tim trainee theo id - Để get ra data ->update
    TraineeDTO findById(Long id);

    // Luu Trainee
    TraineeDTO saveTrainee(TraineeDTO traineeDTO);

    // delete trainees
    void deleteTrainee(long[] ids);

    // get số lượng trainee trong danh sách - Để phân trang
    int getTotalTrainees();

    /*TraineeDTO insert(TraineeDTO traineeDTO);*/

    void saveCourseAssign(long[] ids);

    List<TraineeCourseDTO> findCourseOnTraineeId(long id);

    // delete course assigned to trainee
    void deleteTraineeCourse(long[] ids);

    // find trainee by user id
     TraineeDTO findTraineeByUserId(long id);

}
