package com.laptrinhjavaweb.controller.trainingStaff;

import com.laptrinhjavaweb.dto.RequestCourseDTO;
import com.laptrinhjavaweb.service.IRequestCourseService;
import com.laptrinhjavaweb.service.ITraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "requestCourseControllerOfTrainingStaff")
public class RequestCourseFromTrainee {
    @Autowired
    IRequestCourseService iRequestCourseService;

    @RequestMapping(value = "/training-staff/request/course", method = RequestMethod.GET)
    public ModelAndView requestCourse(@RequestParam("page") int page, @RequestParam int limit){
        RequestCourseDTO requestCourseDTO = new RequestCourseDTO();
        ModelAndView mav = new ModelAndView("trainingStaff/request-course-list");
        requestCourseDTO.setPage(page);
        requestCourseDTO.setLimit(limit);
        Pageable pageable = new PageRequest(page -1, limit);
        requestCourseDTO.setListResult(iRequestCourseService.findAll(pageable));
        requestCourseDTO.setTotalItem(iRequestCourseService.getTotalRequestCourse());
        requestCourseDTO.setTotalPage((int) Math.ceil((double) requestCourseDTO.getTotalItem() / requestCourseDTO.getLimit()));
        mav.addObject("model", requestCourseDTO);
        return mav;
    }
}
