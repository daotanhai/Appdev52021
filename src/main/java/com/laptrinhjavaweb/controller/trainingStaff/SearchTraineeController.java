package com.laptrinhjavaweb.controller.trainingStaff;

import com.laptrinhjavaweb.dto.TraineeDTO;
import com.laptrinhjavaweb.service.ISearchTraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "searchTraineeControllerOfTrainingStaff")
public class SearchTraineeController {
    @Autowired
    private ISearchTraineeService iSearchTraineeService;

    // Hiển thị
    @RequestMapping(value = {"/training-staff/trainee/search"}, method = RequestMethod.GET)
    public String searchTrainee(Model model) {
        model.addAttribute("trainee", new TraineeDTO());
        return "trainingStaff/search-trainee";
    }

    // Nếu USER KO NHẬP CÁC FIELDS KHÁC THÌ NÓ SẼ LÀ "". ko phải empty
    // Nhận data và gọi service
    @RequestMapping(value = "/training-staff/trainee/search/list", method = RequestMethod.POST)
    public ModelAndView traineeList(@ModelAttribute("traineeSearchSubmit") TraineeDTO traineeDTO) {
        ModelAndView mav = new ModelAndView("trainingStaff/search-list-trainee");
        List<TraineeDTO> traineeDTOList;
        TraineeDTO traineeDTOListResult = new TraineeDTO();
        traineeDTOList = iSearchTraineeService.findTrainees(traineeDTO);

        traineeDTOListResult.setListResult(traineeDTOList);

        mav.addObject("traineeDTOListResult", traineeDTOListResult);
        return mav;
    }
}
