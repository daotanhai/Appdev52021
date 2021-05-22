package com.laptrinhjavaweb.api.trainee;

import com.laptrinhjavaweb.dto.TraineeDTO;
import com.laptrinhjavaweb.service.ITraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "traineeAPI")
public class TraineeAPI {
    @Autowired
    private ITraineeService iTraineeService;

    @PutMapping(value = "/api/trainee/role")
    public TraineeDTO updateTrainee(@RequestBody TraineeDTO traineeDTO) {
        return iTraineeService.saveTrainee(traineeDTO);
    }
}
