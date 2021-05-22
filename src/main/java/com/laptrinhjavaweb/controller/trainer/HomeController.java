package com.laptrinhjavaweb.controller.trainer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerOfTrainer")
public class HomeController {
    @RequestMapping(value = "/trainer/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage() {
        return new ModelAndView("trainer/home");
    }
}
