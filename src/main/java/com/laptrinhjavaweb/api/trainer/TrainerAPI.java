package com.laptrinhjavaweb.api.trainer;

import com.laptrinhjavaweb.dto.TrainerDTO;
import com.laptrinhjavaweb.service.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "trainerAPI")
public class TrainerAPI {
    @Autowired
    private ITrainerService iTrainerService;

    @PutMapping(value = "/api/trainer/role")
    public TrainerDTO updateTrainer(@RequestBody TrainerDTO trainerDTO) {
        return iTrainerService.saveTrainerForTrainer(trainerDTO);
    }
}
