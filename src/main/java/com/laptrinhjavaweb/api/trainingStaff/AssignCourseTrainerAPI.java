package com.laptrinhjavaweb.api.trainingStaff;

import com.laptrinhjavaweb.service.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "assignCourseTrainerAPI")
public class AssignCourseTrainerAPI {
    @Autowired
    private ITrainerService iTrainerService;

    @PostMapping(value = "/api/trainer/assign")
    public long[] assignCourse(@RequestBody long[] ids) {
        iTrainerService.saveCourseAssign(ids);
        return ids;
    }

    @DeleteMapping(value = "/api/trainer/assign")
    public void delete(@RequestBody long[] ids) {
        iTrainerService.deleteTrainerCourse(ids);
    }
}
