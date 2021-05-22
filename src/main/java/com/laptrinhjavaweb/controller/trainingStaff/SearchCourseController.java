package com.laptrinhjavaweb.controller.trainingStaff;

import com.laptrinhjavaweb.dto.CourseDTO;
import com.laptrinhjavaweb.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "searchCourseController")
public class SearchCourseController {
    @Autowired
    ICourseService iCourseService;

    @RequestMapping(value = {"/training-staff/course/search"}, method = RequestMethod.GET)
    public String searchCourse(Model model) {
        model.addAttribute("course", new CourseDTO());
        return "trainingStaff/search-course";
    }

    @RequestMapping(value = "/training-staff/course/list", method = RequestMethod.POST)
    public ModelAndView courseList(@ModelAttribute("courseSubmit") CourseDTO courseDTO) {
        ModelAndView mav = new ModelAndView("trainingStaff/search-course-list");
        List<CourseDTO> courseDTOList = new ArrayList<>();
        CourseDTO courseDTOListResult = new CourseDTO();
        courseDTOList = iCourseService.findCourses(courseDTO);
        courseDTOListResult.setListResult(courseDTOList);
        mav.addObject("courseDTOListResult", courseDTOListResult);
        return mav;
    }
}
