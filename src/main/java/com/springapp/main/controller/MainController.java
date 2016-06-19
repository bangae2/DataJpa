package com.springapp.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by bangae11 on 2016-06-18.
 */
@Controller
public class MainController {
    @RequestMapping("/")
    public String login(Model model) {

        return "main/index";
    }

    @RequestMapping(value="/main", method = RequestMethod.POST)
    public String main(Model model) {
        return "main/index";
    }
}
