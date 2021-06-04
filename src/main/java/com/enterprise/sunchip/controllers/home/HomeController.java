package com.enterprise.sunchip.controllers.home;
import Database.Entities.User;
import com.enterprise.sunchip.services.DbService;
//import com.enterprise.sunchip.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@RestController
public class HomeController {

    @Autowired
    DbService dbService;

    @Autowired
    DbService sesssionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homeView()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/home/Home");
        return mv;
    }

    @RequestMapping(value = "LoginAdmin", method = RequestMethod.GET)
    public ModelAndView toLoginPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/Admin");
        return mv;

    }

    @RequestMapping(value = "LoginAdmin", method = RequestMethod.POST)
    public ModelAndView loginAction(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        User currentUser = dbService.BEContext.User.GetByUsernameAndPassword(username, password);

        if (currentUser == null) {
//            sesssionService.storeTokenInLocalCashe(request, "");
            mv.addObject("errorMessage", "sorry, you did not input correct credentials!");
        } else {
//            sesssionService.storeTokenInLocalCashe(request, currentUser.HashUsername);
            mv.addObject("errorMessage", "sorry, successful login. But admin page has not been implemented!");
        }

        return mv;
    }

    @RequestMapping(value = "ForgotPassword", method = RequestMethod.GET)
    public ModelAndView forgotPassword() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, forgot password has not been implemented yet!");
        return mv;
    }


    //dbService.BEContext.User.RegisterNewUser("Yas", "Ql", "JonSnow", "Ghost", User.UserType.ADMIN);

}
