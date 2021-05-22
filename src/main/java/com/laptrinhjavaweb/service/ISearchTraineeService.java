package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.TraineeDTO;

import java.util.List;

public interface ISearchTraineeService {
    /*TraineeDTO findByTraineeUserName(SearchTraineeDTO searchTraineeDTO);
    TraineeDTO findTrainee(SearchTraineeDTO searchTraineeDTO);*/
    List<TraineeDTO> findTrainees(TraineeDTO TraineeDTO);
}
