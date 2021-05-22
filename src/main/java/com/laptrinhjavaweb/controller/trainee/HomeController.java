package com.laptrinhjavaweb.controller.trainee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerOfTrainee")
public class HomeController {
    @RequestMapping(value = "/trainee/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage() {
        return new ModelAndView("trainee/home");
    }
}
