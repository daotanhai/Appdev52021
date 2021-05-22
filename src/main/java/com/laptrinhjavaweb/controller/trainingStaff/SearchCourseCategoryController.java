package com.laptrinhjavaweb.controller.trainingStaff;

import com.laptrinhjavaweb.dto.CourseCategoryDTO;
import com.laptrinhjavaweb.service.ICourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "searchCourseCategoryController")
public class SearchCourseCategoryController {
    @Autowired
    ICourseCategoryService iCourseCategoryService;

    @RequestMapping(value = {"/training-staff/course-category/search"}, method = RequestMethod.GET)
    public String searchCourseCategory(Model model) {
        model.addAttribute("courseCategory", new CourseCategoryDTO());
        return "trainingStaff/search-course-category";
    }

    @RequestMapping(value = "/training-staff/course-category/list", method = RequestMethod.POST)
    public ModelAndView courseCategoryList(@ModelAttribute("courseCategorySubmit") CourseCategoryDTO courseCategoryDTO) {
        ModelAndView mav = new ModelAndView("trainingStaff/search-list-course-category");
        List<CourseCategoryDTO> courseCategoryDTOList = new ArrayList<>();
        CourseCategoryDTO courseCategoryDTOListResult = new CourseCategoryDTO();
        courseCategoryDTOList.add(iCourseCategoryService.findCourseCategory(courseCategoryDTO));
        courseCategoryDTOListResult.setListResult(courseCategoryDTOList);
        mav.addObject("courseCategoryDTOList", courseCategoryDTOListResult);
        return mav;
    }
}
