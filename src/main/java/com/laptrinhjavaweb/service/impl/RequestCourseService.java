package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.RequestCourseConverter;
import com.laptrinhjavaweb.dto.RequestCourseDTO;
import com.laptrinhjavaweb.entity.CourseEntity;
import com.laptrinhjavaweb.entity.RequestCourseEntity;
import com.laptrinhjavaweb.entity.TraineeEntity;
import com.laptrinhjavaweb.repository.CourseRepository;
import com.laptrinhjavaweb.repository.RequestCourseRepository;
import com.laptrinhjavaweb.repository.TraineeRepository;
import com.laptrinhjavaweb.service.IRequestCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RequestCourseService implements IRequestCourseService {
    @Autowired
    RequestCourseRepository requestCourseRepository;
    @Autowired
    TraineeRepository traineeRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    RequestCourseConverter requestCourseConverter;
    @Override
    @Transactional
    public void requestCourse(long[] ids) {
        // Trong mảng gửi về, thì phần tử đầu tiên của ids là id của trainee
        long traineeId = ids[0];
        TraineeEntity traineeEntity = traineeRepository.findOne(traineeId);
        for (int i=1;i<ids.length;i++){
            RequestCourseEntity requestCourseEntity = new RequestCourseEntity();
            CourseEntity courseEntity = courseRepository.findOne(ids[i]);
            requestCourseEntity.setCourseRequest(courseEntity);
            // lap bao nhieu lan thi set bao nhieu lan - chua optimized
            requestCourseEntity.setTraineeRequest(traineeEntity);
            requestCourseRepository.save(requestCourseEntity);
        }
    }

    @Override
    public int getTotalRequestCourse() {
        return (int) requestCourseRepository.count();
    }

    @Override
    public List<RequestCourseDTO> findAll(Pageable pageable) {
        List<RequestCourseDTO> models = new ArrayList<>();
        List<RequestCourseEntity> requestCourseEntities = requestCourseRepository.findAll(pageable).getContent();
        for (RequestCourseEntity items : requestCourseEntities){
            RequestCourseDTO requestCourseDTO = new RequestCourseDTO();
            requestCourseDTO = requestCourseConverter.toDTO(items);
            requestCourseDTO.setCourseId(items.getCourseRequest().getId());
            requestCourseDTO.setTraineeId(items.getTraineeRequest().getId());
            models.add(requestCourseDTO);
        }
        return models;
    }
}
