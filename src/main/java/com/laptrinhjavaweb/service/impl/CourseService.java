package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CourseCategoryConverter;
import com.laptrinhjavaweb.converter.CourseConverter;
import com.laptrinhjavaweb.dto.CourseDTO;
import com.laptrinhjavaweb.entity.*;
import com.laptrinhjavaweb.repository.*;
import com.laptrinhjavaweb.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService implements ICourseService {

    @Autowired
    CourseConverter courseConverter;

    @Autowired
    ICourseRepository ICourseRepository;

    @Autowired
    ICourseCategoryRepository ICourseCategoryRepository;

    @Autowired
    CourseCategoryConverter courseCategoryConverter;

    @Autowired
    ITraineeCourseRepository ITraineeCourseRepository;

    @Autowired
    ITraineeRepository ITraineeRepository;

    @Autowired
    IUserRepository IUserRepository;

    @Autowired
    ITrainerRepository ITrainerRepository;

    @Autowired
    ITrainerCourseRepository ITrainerCourseRepository;

    @Override
    public List<CourseDTO> findAll(Pageable pageable) {
        List<CourseDTO> models = new ArrayList<CourseDTO>();
        List<CourseEntity> entities = ICourseRepository.findAll(pageable).getContent();
        for (CourseEntity items : entities) {
            CourseDTO courseDTO = courseConverter.toDTO(items);
            models.add(courseDTO);
        }
        return models;
    }

    @Override
    public List<CourseDTO> findAllCourse() {
        List<CourseDTO> models = new ArrayList<>();
        List<CourseEntity> entities = ICourseRepository.findAll();
        for (CourseEntity items : entities) {
            CourseDTO courseDTO = courseConverter.toDTO(items);
            models.add(courseDTO);
        }
        return models;
    }

    @Override
    public int getTotalCourse() {
        return (int) ICourseRepository.count();
    }

    @Override
    public CourseDTO findById(long id) {
        return courseConverter.toDTO(ICourseRepository.findOne(id));
    }

    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        CourseCategoryEntity category = ICourseCategoryRepository.findByCourseCategoryNameCode(courseDTO.getCourseCategoryNameCode());
        CourseEntity courseEntity = new CourseEntity();
        // UPDATE
        if (courseDTO.getId() != null) {
            CourseEntity oldCourse = ICourseRepository.findOne(courseDTO.getId());
            // UPDATE THỂ LOẠI
            oldCourse.setCourseCategory(category);
            // UPDATE Thể loại xong update các
            // fields khác thuộc thuần course
            courseEntity = courseConverter.toEntity(oldCourse, courseDTO);
        }
        // ADD NEW COURSE
        else if (courseDTO.getId() == null) {
            courseEntity = courseConverter.toEntity(courseDTO);
            courseEntity.setCourseCategory(category);
        }
        return courseConverter.toDTO(ICourseRepository.save(courseEntity));

    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long id : ids) {
            ICourseRepository.delete(id);
        }
    }

    @Override
    public Map<String, String> findAll() {
        Map<String, String> result = new HashMap<String, String>();
        List<CourseEntity> entities = ICourseRepository.findAll();
        for (CourseEntity item : entities) {
            result.put(item.getCourseName(), item.getCourseName());
        }
        return result;
    }

    @Override
    public List<CourseDTO> findCourses(CourseDTO courseDTO) {
        List<CourseDTO> models = new ArrayList<>();
        CourseDTO courseDTO1 = new CourseDTO();
        int count = 0;
        List<String> results = new ArrayList<>();
        results.add(courseDTO.getCourseName());
        results.add(courseDTO.getCourseCategoryNameCode());
        results.add(courseDTO.getShortDescription());
        for (String result : results) {
            if (!result.equalsIgnoreCase("")) {
                count++;
            }
        }
        // TH1: User gui ve 1 trong 3 fields
        if (count == 1) {
            if (!courseDTO.getCourseName().equalsIgnoreCase("")) {
                List<CourseEntity> courseEntities = ICourseRepository.findCourseEntitiesByCourseName(courseDTO.getCourseName());
                for (CourseEntity item : courseEntities) {
                    courseDTO1 = courseConverter.toDTO(item);
                    models.add(courseDTO1);
                }
            } else if (!courseDTO.getCourseCategoryNameCode().equalsIgnoreCase("")) {
                List<CourseEntity> courseEntities = ICourseRepository.findCourseEntitiesByCourseCategory_CourseCategoryNameCode(courseDTO.getCourseCategoryNameCode());
                for (CourseEntity item : courseEntities) {
                    courseDTO1 = courseConverter.toDTO(item);
                    models.add(courseDTO1);
                }
            } else if (!courseDTO.getShortDescription().equalsIgnoreCase("")) {
                List<CourseEntity> courseEntities = ICourseRepository.findCourseEntitiesByShortDescription(courseDTO.getShortDescription());
                for (CourseEntity item : courseEntities) {
                    courseDTO1 = courseConverter.toDTO(item);
                    models.add(courseDTO1);
                }
            }
        }
        // TH2: User gui ve 2 trong 3 fields
        // 1: Course name + course category name code
        // 2: course category name code + description
        // 3: course name + description
        if (count == 2) {
            if (!courseDTO.getCourseName().equalsIgnoreCase("") && !courseDTO.getCourseCategoryNameCode().equalsIgnoreCase("")) {
                List<CourseEntity> courseEntities = ICourseRepository.findCourseEntitiesByCourseNameAndCourseCategory_CourseCategoryNameCode(courseDTO.getCourseName(), courseDTO.getCourseCategoryNameCode());
                for (CourseEntity item : courseEntities) {
                    courseDTO1 = courseConverter.toDTO(item);
                    models.add(courseDTO1);
                }
            }
            if (!courseDTO.getCourseCategoryNameCode().equalsIgnoreCase("") && !courseDTO.getShortDescription().equalsIgnoreCase("")) {
                List<CourseEntity> courseEntities = ICourseRepository.findCourseEntitiesByCourseCategory_CourseCategoryNameCodeAndShortDescription(courseDTO.getCourseCategoryNameCode(), courseDTO.getShortDescription());
                for (CourseEntity item : courseEntities) {
                    courseDTO1 = courseConverter.toDTO(item);
                    models.add(courseDTO1);
                }
            }
            if (!courseDTO.getCourseName().equalsIgnoreCase("") && !courseDTO.getShortDescription().equalsIgnoreCase("")) {
                List<CourseEntity> courseEntities = ICourseRepository.findCourseEntitiesByCourseNameAndShortDescription(courseDTO.getCourseName(), courseDTO.getShortDescription());
                for (CourseEntity item : courseEntities) {
                    courseDTO1 = courseConverter.toDTO(item);
                    models.add(courseDTO1);
                }
            }
        }
        return models;
    }

    @Override
    public List<CourseDTO> findCoursesByTraineeId(long id) {
        // long id lúc này là id của user, mình phải tìm ra được trainee đó là ai dựa vào userName
        UserEntity userEntity = IUserRepository.findOne(id);
        TraineeEntity traineeEntity = ITraineeRepository.findTraineeEntityByUserName(userEntity.getUserName());
        long traineeId = traineeEntity.getId();
        List<Long> courseIdList = new ArrayList<>();
        List<TraineeCourseEntity> traineeCourseEntities = new ArrayList<>();
        traineeCourseEntities = ITraineeCourseRepository.findTraineeCourseEntitiesByTraineeEntity_Id(traineeId);
        // get ra het cac id cua course based on trainee id
        for (TraineeCourseEntity item : traineeCourseEntities) {
            courseIdList.add(item.getCourseEntityForTrainee().getId());
        }
        // get ra list course entity based on courseIdList
        List<CourseDTO> courseDTOList = new ArrayList<>();
        CourseEntity courseEntity = new CourseEntity();
        for (Long courseId : courseIdList) {
            courseEntity = ICourseRepository.findOne(courseId);
            courseDTOList.add(courseConverter.toDTO(courseEntity));
        }
        return courseDTOList;
    }

    @Override
    public List<CourseDTO> findCoursesByTrainerId(long id) {
        UserEntity userEntity = IUserRepository.findOne(id);
        TrainerEntity trainerEntity = ITrainerRepository.findTrainerEntityByUserName(userEntity.getUserName());
        long trainerId = trainerEntity.getId();
        List<Long> courseIdList = new ArrayList<>();
        TrainerCourseEntity trainerCourseEntity = new TrainerCourseEntity();
        List<TrainerCourseEntity> trainerCourseEntities = new ArrayList<>();
        trainerCourseEntities = ITrainerCourseRepository.findTrainerCourseEntitiesByTrainerEntity_Id(trainerId);
        for (TrainerCourseEntity item : trainerCourseEntities){
            courseIdList.add(item.getCourseEntityForTrainer().getId());
        }
        // get ra list course entity based on courseIdList
        List<CourseDTO> courseDTOList = new ArrayList<>();
        CourseEntity courseEntity = new CourseEntity();
        for (Long courseId : courseIdList) {
            courseEntity = ICourseRepository.findOne(courseId);
            courseDTOList.add(courseConverter.toDTO(courseEntity));
        }
        return courseDTOList;
    }

}
