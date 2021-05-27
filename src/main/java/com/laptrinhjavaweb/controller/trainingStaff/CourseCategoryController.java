package com.laptrinhjavaweb.controller.trainingStaff;

import com.laptrinhjavaweb.dto.CountCourseCategoryDTO;
import com.laptrinhjavaweb.dto.CourseCategoryDTO;
import com.laptrinhjavaweb.service.ICourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "courseCategoryControllerOfTrainingStaff")
public class CourseCategoryController {
    @Autowired
    ICourseCategoryService iCourseCategoryService;

    @RequestMapping(value = "/training-staff/coursecategory/list", method = RequestMethod.GET)
    public ModelAndView courseCategoryList(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        CourseCategoryDTO courseCategoryDTO = new CourseCategoryDTO();

        ModelAndView mav = new ModelAndView("trainingStaff/course-category-list");
        courseCategoryDTO.setPage(page);
        courseCategoryDTO.setLimit(limit);
        Pageable pageable = new PageRequest(page - 1, limit);
        courseCategoryDTO.setListResult(iCourseCategoryService.findAll(pageable));

        courseCategoryDTO.setTotalItem(iCourseCategoryService.getTotalCourseCategory());
        courseCategoryDTO.setTotalPage((int) Math.ceil((double) courseCategoryDTO.getTotalItem() / courseCategoryDTO.getLimit()));
        mav.addObject("model", courseCategoryDTO);

        return mav;
    }

    @RequestMapping(value = "/training-staff/coursecategory/edit", method = RequestMethod.GET)
    public ModelAndView editCourseCategory(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView mav = new ModelAndView("trainingStaff/course-category-edit");
        CourseCategoryDTO courseCategoryDTO = new CourseCategoryDTO();
        // Có id gửi về nghĩa là đang UPDATE -> sẽ tìm course category đó dựa theo id trả ra trang edit
        if (id != null) {
            courseCategoryDTO = iCourseCategoryService.findById(id);
        }
        //mav.addObject("categories", iCourseCategoryService.findAll());
        mav.addObject("model", courseCategoryDTO);
        return mav;
    }
}
