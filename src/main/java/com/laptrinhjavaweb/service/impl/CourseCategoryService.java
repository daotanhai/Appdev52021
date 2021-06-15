package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CourseCategoryConverter;
import com.laptrinhjavaweb.dto.CountCourseCategoryDTO;
import com.laptrinhjavaweb.dto.CourseCategoryDTO;
import com.laptrinhjavaweb.entity.CourseCategoryEntity;
import com.laptrinhjavaweb.repository.ICourseCategoryRepository;
import com.laptrinhjavaweb.repository.ICourseRepository;
import com.laptrinhjavaweb.service.ICourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseCategoryService implements ICourseCategoryService {

    @Autowired
    CourseCategoryConverter courseCategoryConverter;

    @Autowired
    ICourseCategoryRepository ICourseCategoryRepository;

    @Autowired
    ICourseRepository ICourseRepository;

    @Override
    public List<CourseCategoryDTO> findAll(Pageable pageable) {
        // có 1 cái giỏ để đựng các đối tượng DTO riêng lẻ vừa được converter từ Entity
        List<CourseCategoryDTO> models = new ArrayList<>();
        // SELECT * FROM coursecategory
        // tìm hết các row trong table nhưng theo pageable
        List<CourseCategoryEntity> entities = ICourseCategoryRepository.findAll(pageable).getContent();
        // đổ từng đối tượng vào 1 cái item trung gian, sau đó convert qua DTO và đưa vào DTO list models
        for (CourseCategoryEntity items : entities) {
            CourseCategoryDTO courseCategoryDTO = courseCategoryConverter.toDTO(items);
            models.add(courseCategoryDTO);
        }
        return models;
    }

    @Override
    public CourseCategoryDTO findById(Long id) {
        // Tìm đối tượng courseCategoryEntity thông qua repository( data access layer)
        CourseCategoryEntity courseCategoryEntity = ICourseCategoryRepository.findOne(id);
        // sau khi nhận được đối tượng courseCategoryEntity thì convert qua DTO rồi return
        return courseCategoryConverter.toDTO(courseCategoryEntity);
    }

    @Override
    @Transactional
    public CourseCategoryDTO saveCourseCategory(CourseCategoryDTO courseCategoryDTO) {
        CourseCategoryEntity courseCategoryEntity = new CourseCategoryEntity();
        // update
        if (courseCategoryDTO.getId() != null) {
            CourseCategoryEntity oldCourseCategoryEntity = ICourseCategoryRepository.findOne(courseCategoryDTO.getId());
            courseCategoryEntity = courseCategoryConverter.toEntity(oldCourseCategoryEntity, courseCategoryDTO);
        }
        // thêm mới
        if (courseCategoryDTO.getId() == null) {
            courseCategoryEntity = courseCategoryConverter.toEntity(courseCategoryDTO);
        }
        return courseCategoryConverter.toDTO(ICourseCategoryRepository.save(courseCategoryEntity));
    }

    @Override
    @Transactional
    public void deleteCourseCategory(long[] ids) {
        for (long id : ids) {
            ICourseCategoryRepository.delete(id);
        }
    }

    @Override
    public int getTotalCourseCategory() {
        return (int) ICourseCategoryRepository.count();
    }

    @Override
    public Map<String, String> findAll() {
        Map<String, String> result = new HashMap<String, String>();
        List<CourseCategoryEntity> entities = ICourseCategoryRepository.findAll();
        for (CourseCategoryEntity item : entities) {
            result.put(item.getCourseCategoryNameCode(), item.getNameCourseCategory());
        }
        return result;
    }

    @Override
    public CourseCategoryDTO findCourseCategory(CourseCategoryDTO courseCategoryDTO) {
        CourseCategoryEntity courseCategoryEntity = new CourseCategoryEntity();
        if (!courseCategoryDTO.getCourseCategoryNameCode().equalsIgnoreCase("")) {
            courseCategoryEntity = ICourseCategoryRepository.findByCourseCategoryNameCode(courseCategoryDTO.getCourseCategoryNameCode());
        } else if (!courseCategoryDTO.getNameCourseCategory().equalsIgnoreCase("")) {
            courseCategoryEntity = ICourseCategoryRepository.findCourseCategoryEntityByNameCourseCategory(courseCategoryDTO.getNameCourseCategory());
        } else if (!courseCategoryDTO.getCourseCategoryDescription().equalsIgnoreCase("")) {
            courseCategoryEntity = ICourseCategoryRepository.findCourseCategoryEntityByCourseCategoryDescription(courseCategoryDTO.getCourseCategoryDescription());
        }
        return courseCategoryConverter.toDTO(courseCategoryEntity);
    }

    @Override
    public List<CountCourseCategoryDTO> countCourseCategory() {
        List<CountCourseCategoryDTO> countCourseCategoryDTOS = new ArrayList<>();
        List<CourseCategoryEntity> courseCategoryEntities = ICourseCategoryRepository.findAll();

        for (int i=0; i<courseCategoryEntities.size();i++){
            CountCourseCategoryDTO countCourseCategoryDTO = new CountCourseCategoryDTO();
            long countNum = ICourseRepository.countCourseEntitiesByCourseCategory_CourseCategoryNameCode(courseCategoryEntities.get(i).getCourseCategoryNameCode());
            countCourseCategoryDTO.setId(courseCategoryEntities.get(i).getId());
            countCourseCategoryDTO.setCourseCategoryNameCode(courseCategoryEntities.get(i).getCourseCategoryNameCode());
            countCourseCategoryDTO.setNumberOfCourses(countNum);
            countCourseCategoryDTOS.add(i,countCourseCategoryDTO);
        }
        return countCourseCategoryDTOS;
    }

}
