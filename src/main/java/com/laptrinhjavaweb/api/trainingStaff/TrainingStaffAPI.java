package com.laptrinhjavaweb.api.trainingStaff;

import com.laptrinhjavaweb.dto.TraineeDTO;
import com.laptrinhjavaweb.service.ITraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "TraineeAPIOfTrainingStaff")
public class TrainingStaffAPI {
    @Autowired
    private ITraineeService iTraineeService;

    @PostMapping(value = "/api/trainee")
    public TraineeDTO createTrainee(@RequestBody TraineeDTO traineeDTO) {
        return iTraineeService.saveTrainee(traineeDTO);
    }

    @PostMapping(value = "/api/ids")
    public long[] insertIds(@RequestParam long[] ids) {
        return null;
    }

    @PutMapping(value = "/api/trainee")
    public TraineeDTO updateTrainee(@RequestBody TraineeDTO traineeDTO) {
        return iTraineeService.saveTrainee(traineeDTO);
    }

    @DeleteMapping(value = "/api/trainee")
    public void deleteTrainee(@RequestBody long[] ids) {
        iTraineeService.deleteTrainee(ids);
    }
}

