package com.laptrinhjavaweb.controller.trainingStaff;

import com.laptrinhjavaweb.dto.CountCourseCategoryDTO;
import com.laptrinhjavaweb.dto.CourseDTO;
import com.laptrinhjavaweb.service.ICourseCategoryService;
import com.laptrinhjavaweb.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "courseControllerOfTrainingStaff")
public class CourseController {

    @Autowired
    private ICourseService iCourseService;

    @Autowired
    private ICourseCategoryService iCourseCategoryService;

    @RequestMapping(value = "/training-staff/course/list", method = RequestMethod.GET)
    public ModelAndView courseList(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        CourseDTO courseDTO = new CourseDTO();

        ModelAndView mav = new ModelAndView("trainingStaff/course-list");
        courseDTO.setPage(page);
        courseDTO.setLimit(limit);
        Pageable pageable = new PageRequest(page - 1, limit);
        courseDTO.setListResult(iCourseService.findAll(pageable));

        courseDTO.setTotalItem(iCourseService.getTotalCourse());
        courseDTO.setTotalPage((int) Math.ceil((double) courseDTO.getTotalItem() / courseDTO.getLimit()));
        mav.addObject("model", courseDTO);

        return mav;
    }

    @RequestMapping(value = "/training-staff/course/edit", method = RequestMethod.GET)
    public ModelAndView editCourse(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView mav = new ModelAndView("trainingStaff/course-edit");
        CourseDTO courseDTO = new CourseDTO();
        if (id != null) {
            courseDTO = iCourseService.findById(id);
        }
        mav.addObject("categories", iCourseCategoryService.findAll());
        mav.addObject("model", courseDTO);
        return mav;
    }

}
