package com.springapp.main.controller;

import com.springapp.videos.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by bangae11 on 2016-06-18.
 */
@Controller
public class MainController {
    @Autowired
    private VideosService videosService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("videos",this.videosService.findAll());
        return "main/index";
    }

    @RequestMapping(value="/main", method = RequestMethod.POST)
    public String main(Model model) {
        model.addAttribute("videos",this.videosService.findAll());
        return "main/index";
    }
}
