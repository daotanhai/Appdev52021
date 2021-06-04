package com.laptrinhjavaweb.api.trainer;

import com.laptrinhjavaweb.service.IRequestCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "requestCourseAPIForTrainer")
public class RequestCourse {
    @Autowired
    private IRequestCourseService iRequestCourseService;

    @PostMapping(value = "/api/trainer/course")
    public void requestCourse(@RequestBody long[] ids){
        iRequestCourseService.requestCourse(ids);
    }
}
