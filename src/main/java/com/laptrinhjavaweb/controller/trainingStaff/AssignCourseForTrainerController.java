package com.laptrinhjavaweb.controller.trainingStaff;

import com.laptrinhjavaweb.dto.CourseDTO;
import com.laptrinhjavaweb.dto.TrainerCourseDTO;
import com.laptrinhjavaweb.dto.TrainerDTO;
import com.laptrinhjavaweb.service.ICourseService;
import com.laptrinhjavaweb.service.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "assignTrainerCourseController")
public class AssignCourseForTrainerController {
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private ITrainerService iTrainerService;

    @RequestMapping(value = "/training-staff/trainer/assign-course", method = RequestMethod.GET)
    public ModelAndView assignCourse(@RequestParam(value = "id") long id) {
        ModelAndView mav = new ModelAndView("trainingStaff/assign-course-trainer");
        TrainerDTO trainerDTO = new TrainerDTO();
        CourseDTO courseDTO = new CourseDTO();
        // Hiển thị khóa học của trainer
        trainerDTO = iTrainerService.findById(id);
        List<TrainerCourseDTO> trainerCourseDTOList = new ArrayList<>();
        TrainerCourseDTO trainerCourseDTO = new TrainerCourseDTO();
        trainerCourseDTOList = iTrainerService.findCourseOnTrainerId(id);
        trainerCourseDTO.setListResult(trainerCourseDTOList);
        // Hiển thị list khóa học cho chọn
        courseDTO.setListResult(iCourseService.findAllCourse());
        // Hiển thị list khóa học của trainer đó
        mav.addObject("trainerCourseList", trainerCourseDTO);
        // Để hiển thị tên và id của trainer
        mav.addObject("model", trainerDTO);
        // để show ra list courses
        mav.addObject("courseList", courseDTO);
        return mav;
    }
}
