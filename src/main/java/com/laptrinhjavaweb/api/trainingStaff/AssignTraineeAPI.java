package com.laptrinhjavaweb.api.trainingStaff;

import com.laptrinhjavaweb.service.ITraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "assignCourseTraineeAPI")
public class AssignTraineeAPI {
    @Autowired
    private ITraineeService iTraineeService;

    // Gán khóa học cho học viên
    @PostMapping(value = "/api/assign")
    public long[] assignCourse(@RequestBody long[] ids) {
        iTraineeService.saveCourseAssign(ids);
        return ids;
    }

    @DeleteMapping(value = "/api/assign")
    public void deleteTraineeCourse(@RequestBody long[] ids) {
        iTraineeService.deleteTraineeCourse(ids);
    }
}
