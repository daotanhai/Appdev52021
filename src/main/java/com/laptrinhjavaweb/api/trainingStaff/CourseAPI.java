package com.laptrinhjavaweb.api.trainingStaff;

import com.laptrinhjavaweb.dto.CourseDTO;
import com.laptrinhjavaweb.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "courseAPIOfTrainingStaff")
public class CourseAPI {

    @Autowired
    private ICourseService iCourseService;

    @PostMapping(value = "/api/course")
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO) {
        return iCourseService.save(courseDTO);
    }

    @PutMapping(value = "/api/course")
    public CourseDTO updateCourse(@RequestBody CourseDTO courseDTO) {
        return iCourseService.save(courseDTO);
    }

    @DeleteMapping(value = "/api/course")
    public void deleteCourse(@RequestBody long[] ids) {
        iCourseService.delete(ids);
    }
}
