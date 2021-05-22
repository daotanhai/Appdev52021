package com.laptrinhjavaweb.controller.trainingStaff;

import com.laptrinhjavaweb.dto.CourseDTO;
import com.laptrinhjavaweb.dto.TraineeDTO;
import com.laptrinhjavaweb.service.ICourseService;
import com.laptrinhjavaweb.service.IRoleService;
import com.laptrinhjavaweb.service.ITraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "traineeControllerOfTrainingStaff")
public class TraineeController {
    @Autowired
    private ITraineeService iTraineeService;

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private ICourseService iCourseService;

    @RequestMapping(value = "/training-staff/trainee/edit", method = RequestMethod.GET)
    public ModelAndView traineeEdit(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView mav = new ModelAndView("trainingStaff/create-trainee-account");
        TraineeDTO traineeDTO = new TraineeDTO();
        CourseDTO courseDTO = new CourseDTO();
        if (id != null) {
            traineeDTO = iTraineeService.findById(id);
        }
        // mav.addObject("courseList", iCourseCategoryService.findAll());
        //mav.addObject("courseDTOs", iCourseService.findAll());
        courseDTO.setListResult(iCourseService.findAllCourse());
        mav.addObject("roles", iRoleService.findAll());
        mav.addObject("model", traineeDTO);
        return mav;
    }

    @RequestMapping(value = "/training-staff/trainee/list", method = RequestMethod.GET)
    public ModelAndView traineeList(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        TraineeDTO traineeDTO = new TraineeDTO();
        ModelAndView mav = new ModelAndView("trainingStaff/list-trainee");
        traineeDTO.setPage(page);
        traineeDTO.setLimit(limit);
        Pageable pageable = new PageRequest(page - 1, limit);
        traineeDTO.setListResult(iTraineeService.findAll(pageable));
        traineeDTO.setTotalItem(iTraineeService.getTotalTrainees());
        traineeDTO.setTotalPage((int) Math.ceil((double) traineeDTO.getTotalItem() / traineeDTO.getLimit()));

        mav.addObject("model", traineeDTO);
        return mav;
    }
}
