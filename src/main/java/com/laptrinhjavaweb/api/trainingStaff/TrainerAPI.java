package com.laptrinhjavaweb.api.trainingStaff;

import com.laptrinhjavaweb.dto.TrainerDTO;
import com.laptrinhjavaweb.dto.TrainerForTrainingStaffDTO;
import com.laptrinhjavaweb.service.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "trainerAPIOfTrainingStaff")
public class TrainerAPI {
    @Autowired
    private ITrainerService iTrainerService;

    @PutMapping(value = "/api/trainer")
    public TrainerForTrainingStaffDTO updateTrainer(@RequestBody TrainerForTrainingStaffDTO trainerForTrainingStaffDTO) {
        return iTrainerService.saveTrainerForTrainingStaff(trainerForTrainingStaffDTO);
    }

    @DeleteMapping(value = "/api/trainer")
    public void deleteTrainer(@RequestBody long[] ids) {
        iTrainerService.deleteTrainer(ids);
    }
}
