package com.springapp.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by bangae11 on 2016-06-19.
 */

@Controller
public class UsersController {

    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        return "login/signup";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login/login";
    }
}
