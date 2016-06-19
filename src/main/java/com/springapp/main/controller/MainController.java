package com.springapp.main.controller;

import com.springapp.users.entity.UsersEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;

/**
 * Created by bangae11 on 2016-06-18.
 */
@Controller
public class MainController {
    @RequestMapping("/")
    public String login(Model model) {
        UsersEntity user = (UsersEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "main/index";
    }

    @RequestMapping(value="/main", method = RequestMethod.POST)
    public String main(Model model) {
        UsersEntity user = (UsersEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "main/index";
    }
}
