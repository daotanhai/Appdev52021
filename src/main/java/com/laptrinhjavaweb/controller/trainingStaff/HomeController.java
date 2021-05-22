package com.laptrinhjavaweb.controller.trainingStaff;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerOfTrainingStaff")
public class HomeController {
    // Tất cả các URL của training staff đều phải bắt đầu là /training-staff
    // để apply được giao diện đã chia
    @RequestMapping(value = "/training-staff/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("admin/home");
        return mav;
    }
}
