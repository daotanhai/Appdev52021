package com.laptrinhjavaweb.controller.trainingStaff;

import com.laptrinhjavaweb.dto.TrainerDTO;
import com.laptrinhjavaweb.service.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "trainerControllerOfTrainingStaff")
public class TrainerController {
    @Autowired
    private ITrainerService iTrainerService;

    @RequestMapping(value = "/training-staff/trainer/list", method = RequestMethod.GET)
    public ModelAndView listTrainer(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        TrainerDTO trainerDTO = new TrainerDTO();
        ModelAndView mav = new ModelAndView("trainingStaff/trainer-list");
        trainerDTO.setPage(page);
        trainerDTO.setLimit(limit);
        Pageable pageable = new PageRequest(page - 1, limit);
        trainerDTO.setListResult(iTrainerService.findAll(pageable));
        trainerDTO.setTotalItem(iTrainerService.getTotalTrainers());
        trainerDTO.setTotalPage((int) Math.ceil((double) trainerDTO.getTotalItem() / trainerDTO.getLimit()));
        mav.addObject("model", trainerDTO);
        return mav;
    }

    @RequestMapping(value = "/training-staff/trainer/edit", method = RequestMethod.GET)
    public ModelAndView editTrainer(@RequestParam(value = "id") Long id) {
        ModelAndView mav = new ModelAndView("trainingStaff/trainer-edit");
        TrainerDTO trainerDTO = new TrainerDTO();
        if (id != null) {
            trainerDTO = iTrainerService.findById(id);
        }
        mav.addObject("model", trainerDTO);
        return mav;
    }
}
