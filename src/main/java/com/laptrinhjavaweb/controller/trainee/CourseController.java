package com.laptrinhjavaweb.controller.trainee;

import com.laptrinhjavaweb.dto.CourseDTO;
import com.laptrinhjavaweb.dto.TraineeDTO;
import com.laptrinhjavaweb.service.ICourseService;
import com.laptrinhjavaweb.service.ITraineeService;
import com.laptrinhjavaweb.service.ITrainerService;
import com.laptrinhjavaweb.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "courseListControllerOfTrainee")
public class CourseController {
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private ITraineeService iTraineeService;

    @RequestMapping(value = "/trainee/course/list", method = RequestMethod.GET)
    public ModelAndView courseList(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        long id = customUserDetailsService.getUserId();
        CourseDTO courseDTO = new CourseDTO();
        TraineeDTO traineeDTO = iTraineeService.findTraineeByUserId(id);
        ModelAndView mav = new ModelAndView("/trainee/course-list");
        courseDTO.setPage(page);
        courseDTO.setLimit(limit);
        Pageable pageable = new PageRequest(page - 1, limit);
        courseDTO.setListResult(iCourseService.findAll(pageable));
        courseDTO.setTotalItem(iCourseService.getTotalCourse());
        courseDTO.setTotalPage((int) Math.ceil((double) courseDTO.getTotalItem() / courseDTO.getLimit()));
        mav.addObject("model", courseDTO);
        mav.addObject("traineeDTO",traineeDTO);
        return mav;
    }

    // Khoa hoc cua trainee
    @RequestMapping(value = "/trainee/course/my-course", method = RequestMethod.GET)
    public ModelAndView myCourseList() {
        ModelAndView mav = new ModelAndView("/trainee/course-list");
        // id cua trainee
        long id = customUserDetailsService.getUserId();
        TraineeDTO traineeDTO = iTraineeService.findTraineeByUserId(id);
        List<CourseDTO> courseDTOList = new ArrayList<>();
        CourseDTO courseDTO = new CourseDTO();
        courseDTOList = iCourseService.findCoursesByTraineeId(id);
        courseDTO.setListResult(courseDTOList);
        mav.addObject("model", courseDTO);
        mav.addObject("traineeDTO",traineeDTO);
        return mav;
    }
}
