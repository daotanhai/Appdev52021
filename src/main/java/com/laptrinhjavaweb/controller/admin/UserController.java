package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IRoleService;
import com.laptrinhjavaweb.service.IUserForAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "userControllerOfAdmin")
public class UserController {
    @Autowired
    private IUserForAdminService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/quan-tri/user/list", method = RequestMethod.GET)
    public ModelAndView showList(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        UserDTO userDTO = new UserDTO();
        ModelAndView mav = new ModelAndView("admin/new/list-user");
        userDTO.setPage(page);
        userDTO.setLimit(limit);
        Pageable pageable = new PageRequest(page - 1, limit);
        userDTO.setListResult(userService.findAll(pageable));
        userDTO.setTotalItem(userService.getTotalItem());
        userDTO.setTotalPage((int) Math.ceil((double) userDTO.getTotalItem() / userDTO.getLimit()));
        mav.addObject("model", userDTO);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/user/edit", method = RequestMethod.GET)
    public ModelAndView trainerEdit(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView mav = new ModelAndView("admin/new/create-user");
        UserDTO userDTO = new UserDTO();
        // Nếu có id trả về, thì lúc này nó sẽ tìm ra thông tin của user dựa theo id
        // và trả cho DTO ( nó select từ entity)
        // qua tầng service, service xuống repository
        // Chỉnh sửa user
        if (id != null) {
            userDTO = userService.findById(id);
        }
        mav.addObject("roles", roleService.findAll());
        mav.addObject("models", userDTO);
        return mav;
    }
}
