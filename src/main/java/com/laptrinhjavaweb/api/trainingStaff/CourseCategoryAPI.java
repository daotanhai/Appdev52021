package com.laptrinhjavaweb.api.trainingStaff;

import com.laptrinhjavaweb.dto.CourseCategoryDTO;
import com.laptrinhjavaweb.service.ICourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "courseCategoryAPIOfTrainingStaff")
public class CourseCategoryAPI {

    @Autowired
    private ICourseCategoryService iCourseCategoryService;

    @PostMapping(value = "/api/courseCategory")
    public CourseCategoryDTO createCourseCategory(@RequestBody CourseCategoryDTO courseCategoryDTO) {
        return iCourseCategoryService.saveCourseCategory(courseCategoryDTO);
    }

    @PutMapping(value = "/api/courseCategory")
    public CourseCategoryDTO updateCourseCategory(@RequestBody CourseCategoryDTO courseCategoryDTO) {
        return iCourseCategoryService.saveCourseCategory(courseCategoryDTO);
    }

    @DeleteMapping(value = "/api/courseCategory")
    public void deleteCourseCategory(@RequestBody long[] ids) {
        iCourseCategoryService.deleteCourseCategory(ids);
    }
}
