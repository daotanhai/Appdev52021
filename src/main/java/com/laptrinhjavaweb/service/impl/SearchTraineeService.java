package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.TraineeConverter;
import com.laptrinhjavaweb.dto.TraineeDTO;
import com.laptrinhjavaweb.entity.TraineeEntity;
import com.laptrinhjavaweb.repository.TraineeRepository;
import com.laptrinhjavaweb.service.ISearchTraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchTraineeService implements ISearchTraineeService {
    @Autowired
    TraineeRepository traineeRepository;

    @Autowired
    TraineeConverter traineeConverter;
    /*@Override
    public TraineeDTO findByTraineeUserName(SearchTraineeDTO searchTraineeDTO) {
        TraineeEntity traineeEntity = new TraineeEntity();
        traineeEntity = traineeRepository.findTraineeEntityByUserName(searchTraineeDTO.getUserName());
        return traineeConverter.toDTO(traineeEntity);
    }*/

    /* @Override
     public TraineeDTO findTrainee(SearchTraineeDTO searchTraineeDTO) {
         TraineeEntity traineeEntity = new TraineeEntity();
         // tim theo username
         if (!searchTraineeDTO.getUserName().equalsIgnoreCase("")){
             traineeEntity = traineeRepository.findTraineeEntityByUserName(searchTraineeDTO.getUserName());
         }
         // tim theo language
         else if (!searchTraineeDTO.getMainProgrammingLangugage().equalsIgnoreCase("")){
             traineeEntity = traineeRepository.findTraineeEntityByMainProgrammingLangugage(searchTraineeDTO.getMainProgrammingLangugage());
         }
         else if (!searchTraineeDTO.getToeicScore().equalsIgnoreCase("")){
             traineeEntity = traineeRepository.findTraineeEntityByToeicScore(searchTraineeDTO.getToeicScore());
         }
         return traineeConverter.toDTO(traineeEntity);
     }*/
// fix bug: Làm sao để nó tìm ra đúng row có 2 fields match với dữ liệu trả về
    @Override
    public List<TraineeDTO> findTrainees(TraineeDTO TraineeDTO) {
        // search traineeDTO gui ve max 3 fields: userName, mainProgramminglangugage, toeicScore
        List<TraineeDTO> models = new ArrayList<>();
        TraineeDTO traineeDTO = new TraineeDTO();
        int count = 0;

        List<String> results = new ArrayList<>();
        results.add(TraineeDTO.getUserName());
        results.add(TraineeDTO.getMainProgrammingLangugage());
        results.add(TraineeDTO.getToeicScore());
        for (String result : results) {
            if (!result.equalsIgnoreCase("")) {
                count++;
            }
        }
        if (count == 1) {
            if (!TraineeDTO.getToeicScore().equalsIgnoreCase("")) {
                List<TraineeEntity> traineeEntities = traineeRepository.findTraineeEntityByToeicScore(TraineeDTO.getToeicScore());
                for (TraineeEntity item : traineeEntities) {
                    traineeDTO = traineeConverter.toDTO(item);
                    models.add(traineeDTO);
                }
            }
            if (!TraineeDTO.getUserName().equalsIgnoreCase("")) {
                TraineeEntity traineeEntity = traineeRepository.findTraineeEntityByUserName(TraineeDTO.getUserName());
                traineeDTO = traineeConverter.toDTO(traineeEntity);
                models.add(traineeDTO);
            }
            if (!TraineeDTO.getMainProgrammingLangugage().equalsIgnoreCase("")) {
                List<TraineeEntity> traineeEntities = traineeRepository.findTraineeEntityByMainProgrammingLangugage(TraineeDTO.getMainProgrammingLangugage());
                for (TraineeEntity item : traineeEntities) {
                    traineeDTO = traineeConverter.toDTO(item);
                    models.add(traineeDTO);
                }
            }
        } else if (count == 2) {
            if (!results.get(1).equalsIgnoreCase("") && !results.get(2).equalsIgnoreCase("")) {
                List<TraineeEntity> traineeEntities = traineeRepository.findTraineeEntitiesByToeicScoreAndMainProgrammingLangugage(TraineeDTO.getToeicScore(), TraineeDTO.getMainProgrammingLangugage());
                TraineeDTO traineeDTO1 = new TraineeDTO();
                for (TraineeEntity item : traineeEntities) {
                    traineeDTO1 = traineeConverter.toDTO(item);
                    models.add(traineeDTO1);
                }
            }
        }

        return models;
    }
}
