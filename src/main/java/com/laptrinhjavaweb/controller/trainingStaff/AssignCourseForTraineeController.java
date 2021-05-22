package com.laptrinhjavaweb.controller.trainingStaff;

import com.laptrinhjavaweb.dto.CourseDTO;
import com.laptrinhjavaweb.dto.TraineeCourseDTO;
import com.laptrinhjavaweb.dto.TraineeDTO;
import com.laptrinhjavaweb.service.ICourseService;
import com.laptrinhjavaweb.service.ITraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "assignCourseControllerOfTrainingStaff")
public class AssignCourseForTraineeController {
    @Autowired
    private ICourseService iCourseService;

    @Autowired
    private ITraineeService iTraineeService;

    //Controller hien thi cho thêm khóa học vào học viên
    @RequestMapping(value = "/training-staff/trainee/assign-course", method = RequestMethod.GET)
    public ModelAndView assignCourse(@RequestParam(value = "id") long id) {
        ModelAndView mav = new ModelAndView("trainingStaff/assign-course-trainee");
        TraineeDTO traineeDTO = new TraineeDTO();

        CourseDTO courseDTO = new CourseDTO();
        // Hiển thị khóa học đã đăng ký
        traineeDTO = iTraineeService.findById(id);
        List<TraineeCourseDTO> traineeCourseDTOList = new ArrayList<>();
        TraineeCourseDTO traineeCourseDTO = new TraineeCourseDTO();
        traineeCourseDTOList = iTraineeService.findCourseOnTraineeId(id);
        traineeCourseDTO.setListResult(traineeCourseDTOList);

        // Hiển thị list khóa học cho chọn
        courseDTO.setListResult(iCourseService.findAllCourse());
        // Hiển thị list khóa học của trainee đó
        mav.addObject("traineeCourseList", traineeCourseDTO);
        // Để hiển thị tên và id của trainee
        mav.addObject("model", traineeDTO);
        // để show ra list courses
        mav.addObject("courseList", courseDTO);
        return mav;
    }
}
