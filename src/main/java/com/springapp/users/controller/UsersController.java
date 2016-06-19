package com.springapp.users.controller;

import com.springapp.common.service.MailService;
import com.springapp.common.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bangae11 on 2016-06-19.
 */

@Controller
public class UsersController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UtilService utilService;


    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        return "login/signup";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login/login";
    }

    @RequestMapping(value="/forgotPassword", method = RequestMethod.GET)
    public String forgotPassword() {
        return "login/forgotPassword";
    }

    @RequestMapping(value="/findPassword", method = RequestMethod.POST, produces = {"text/plain;charset=utf-8"})
    @ResponseBody
    public String findPassword(@Param("email")String email) {

        mailService.passwordMailSender(email, utilService.resetPassword());
        return "success";
    }
}
