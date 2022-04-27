package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("index")
public class IndexController {

    @RequestMapping("test")
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("username", "dhjkg");
        mv.setViewName("twt");


        return mv;
    }
}
