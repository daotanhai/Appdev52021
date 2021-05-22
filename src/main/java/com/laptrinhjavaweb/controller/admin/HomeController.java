package com.laptrinhjavaweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/* @Controller (value="homeControllerOfAdmin") public class HomeController { //
 tiêm HomeService vào để dùng // @Autowired // private HomeService
 homeService;
 
 // request url /trang-chu trỏ tới thư mục admin/home
 
 @RequestMapping(value = "/admin", method = RequestMethod.GET) public
 ModelAndView adminPage() { ModelAndView mav = new ModelAndView("admin/home");
 // mav.addObject("menu", homeService.loadMenu()); return mav; }
 
 }*/

@Controller(value = "homeControllerOfAdmin")
public class HomeController {

    @RequestMapping(value = "/quan-tri/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("admin/home");
        return mav;
    }
}