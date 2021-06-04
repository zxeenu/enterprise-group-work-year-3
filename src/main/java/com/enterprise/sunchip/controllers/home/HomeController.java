package com.enterprise.sunchip.controllers.home;
import com.enterprise.sunchip.services.DbService;
import com.enterprise.sunchip.services.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homeView()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/home/Home");
        return mv;
    }







}
