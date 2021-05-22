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
    CourseRepository courseRepository;

    @Autowired
    CourseCategoryRepository courseCategoryRepository;

    @Autowired
    CourseCategoryConverter courseCategoryConverter;

    @Autowired
    TraineeCourseRepository traineeCourseRepository;

    @Autowired
    TraineeRepository traineeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    TrainerCourseRepository trainerCourseRepository;

    @Override
    public List<CourseDTO> findAll(Pageable pageable) {
        List<CourseDTO> models = new ArrayList<CourseDTO>();
        List<CourseEntity> entities = courseRepository.findAll(pageable).getContent();
        for (CourseEntity items : entities) {
            CourseDTO courseDTO = courseConverter.toDTO(items);
            models.add(courseDTO);
        }
        return models;
    }

    @Override
    public List<CourseDTO> findAllCourse() {
        List<CourseDTO> models = new ArrayList<>();
        List<CourseEntity> entities = courseRepository.findAll();
        for (CourseEntity items : entities) {
            CourseDTO courseDTO = courseConverter.toDTO(items);
            models.add(courseDTO);
        }
        return models;
    }

    @Override
    public int getTotalCourse() {
        return (int) courseRepository.count();
    }

    @Override
    public CourseDTO findById(long id) {
        return courseConverter.toDTO(courseRepository.findOne(id));
    }

    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        CourseCategoryEntity category = courseCategoryRepository.findByCourseCategoryNameCode(courseDTO.getCourseCategoryNameCode());
        CourseEntity courseEntity = new CourseEntity();
        // UPDATE
        if (courseDTO.getId() != null) {
            CourseEntity oldCourse = courseRepository.findOne(courseDTO.getId());
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
        return courseConverter.toDTO(courseRepository.save(courseEntity));

    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long id : ids) {
            courseRepository.delete(id);
        }
    }

    @Override
    public Map<String, String> findAll() {
        Map<String, String> result = new HashMap<String, String>();
        List<CourseEntity> entities = courseRepository.findAll();
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
                List<CourseEntity> courseEntities = courseRepository.findCourseEntitiesByCourseName(courseDTO.getCourseName());
                for (CourseEntity item : courseEntities) {
                    courseDTO1 = courseConverter.toDTO(item);
                    models.add(courseDTO1);
                }
            } else if (!courseDTO.getCourseCategoryNameCode().equalsIgnoreCase("")) {
                List<CourseEntity> courseEntities = courseRepository.findCourseEntitiesByCourseCategory_CourseCategoryNameCode(courseDTO.getCourseCategoryNameCode());
                for (CourseEntity item : courseEntities) {
                    courseDTO1 = courseConverter.toDTO(item);
                    models.add(courseDTO1);
                }
            } else if (!courseDTO.getShortDescription().equalsIgnoreCase("")) {
                List<CourseEntity> courseEntities = courseRepository.findCourseEntitiesByShortDescription(courseDTO.getShortDescription());
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
                List<CourseEntity> courseEntities = courseRepository.findCourseEntitiesByCourseNameAndCourseCategory_CourseCategoryNameCode(courseDTO.getCourseName(), courseDTO.getCourseCategoryNameCode());
                for (CourseEntity item : courseEntities) {
                    courseDTO1 = courseConverter.toDTO(item);
                    models.add(courseDTO1);
                }
            }
            if (!courseDTO.getCourseCategoryNameCode().equalsIgnoreCase("") && !courseDTO.getShortDescription().equalsIgnoreCase("")) {
                List<CourseEntity> courseEntities = courseRepository.findCourseEntitiesByCourseCategory_CourseCategoryNameCodeAndShortDescription(courseDTO.getCourseCategoryNameCode(), courseDTO.getShortDescription());
                for (CourseEntity item : courseEntities) {
                    courseDTO1 = courseConverter.toDTO(item);
                    models.add(courseDTO1);
                }
            }
            if (!courseDTO.getCourseName().equalsIgnoreCase("") && !courseDTO.getShortDescription().equalsIgnoreCase("")) {
                List<CourseEntity> courseEntities = courseRepository.findCourseEntitiesByCourseNameAndShortDescription(courseDTO.getCourseName(), courseDTO.getShortDescription());
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
        UserEntity userEntity = userRepository.findOne(id);
        TraineeEntity traineeEntity = traineeRepository.findTraineeEntityByUserName(userEntity.getUserName());
        long traineeId = traineeEntity.getId();
        List<Long> courseIdList = new ArrayList<>();
        List<TraineeCourseEntity> traineeCourseEntities = new ArrayList<>();
        traineeCourseEntities = traineeCourseRepository.findTraineeCourseEntitiesByTraineeEntity_Id(traineeId);
        // get ra het cac id cua course based on trainee id
        for (TraineeCourseEntity item : traineeCourseEntities) {
            courseIdList.add(item.getCourseEntityForTrainee().getId());
        }
        // get ra list course entity based on courseIdList
        List<CourseDTO> courseDTOList = new ArrayList<>();
        CourseEntity courseEntity = new CourseEntity();
        for (Long courseId : courseIdList) {
            courseEntity = courseRepository.findOne(courseId);
            courseDTOList.add(courseConverter.toDTO(courseEntity));
        }
        return courseDTOList;
    }

    @Override
    public List<CourseDTO> findCoursesByTrainerId(long id) {
        UserEntity userEntity = userRepository.findOne(id);
        TrainerEntity trainerEntity = trainerRepository.findTrainerEntityByUserName(userEntity.getUserName());
        long trainerId = trainerEntity.getId();
        List<Long> courseIdList = new ArrayList<>();
        TrainerCourseEntity trainerCourseEntity = new TrainerCourseEntity();
        List<TrainerCourseEntity> trainerCourseEntities = new ArrayList<>();
        trainerCourseEntities = trainerCourseRepository.findTrainerCourseEntitiesByTrainerEntity_Id(trainerId);
        for (TrainerCourseEntity item : trainerCourseEntities){
            courseIdList.add(item.getCourseEntityForTrainer().getId());
        }
        // get ra list course entity based on courseIdList
        List<CourseDTO> courseDTOList = new ArrayList<>();
        CourseEntity courseEntity = new CourseEntity();
        for (Long courseId : courseIdList) {
            courseEntity = courseRepository.findOne(courseId);
            courseDTOList.add(courseConverter.toDTO(courseEntity));
        }
        return courseDTOList;
    }

}
