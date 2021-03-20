package com.enterprise.sunchip.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

    @RequestMapping("/")
    public ModelAndView CreateView()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

}
