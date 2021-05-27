package com.laptrinhjavaweb.controller.trainingStaff;

import com.laptrinhjavaweb.dto.CountCourseCategoryDTO;
import com.laptrinhjavaweb.service.ICourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "countController")
public class CountController {
    @Autowired
    ICourseCategoryService iCourseCategoryService;

    @RequestMapping(value = "/training-staff/count", method = RequestMethod.GET)
    public ModelAndView countCourseCategories(){
        ModelAndView mav = new ModelAndView("trainingStaff/count-course-category");
        CountCourseCategoryDTO countCourseCategoryDTO = new CountCourseCategoryDTO();
        countCourseCategoryDTO.setListResult(iCourseCategoryService.countCourseCategory());
        mav.addObject("countCourseCategories",countCourseCategoryDTO);
        return mav;
    }
}
