package com.laptrinhjavaweb.controller.trainer;

import com.laptrinhjavaweb.dto.CourseDTO;
import com.laptrinhjavaweb.service.ICourseService;
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

@Controller(value = "courseListControllerOfTrainer")
public class CourseController {
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    private ICourseService iCourseService;

    @RequestMapping(value = "/trainer/course/list", method = RequestMethod.GET)
    public ModelAndView courseList(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        CourseDTO courseDTO = new CourseDTO();
        ModelAndView mav = new ModelAndView("trainer/course-list");
        courseDTO.setPage(page);
        courseDTO.setLimit(limit);
        Pageable pageable = new PageRequest(page - 1, limit);
        courseDTO.setListResult(iCourseService.findAll(pageable));
        courseDTO.setTotalItem(iCourseService.getTotalCourse());
        courseDTO.setTotalPage((int) Math.ceil((double) courseDTO.getTotalItem() / courseDTO.getLimit()));
        mav.addObject("model", courseDTO);
        return mav;
    }

    // Khoa hoc cua trainer
    @RequestMapping(value = "/trainer/course/my-course", method = RequestMethod.GET)
    public ModelAndView myCourseList() {
        ModelAndView mav = new ModelAndView("/trainer/course-list");
        // id cua trainer
        long id = customUserDetailsService.getUserId();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        CourseDTO courseDTO = new CourseDTO();
        courseDTOList = iCourseService.findCoursesByTrainerId(id);
        courseDTO.setListResult(courseDTOList);
        mav.addObject("model", courseDTO);
        return mav;
    }
}
