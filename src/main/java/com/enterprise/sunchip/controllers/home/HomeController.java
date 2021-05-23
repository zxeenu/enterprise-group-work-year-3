package com.enterprise.sunchip.controllers.home;
import Database.Entities.User;
import com.enterprise.sunchip.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class HomeController {

    @Autowired
    DbService dbService;

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
    public ModelAndView loginAction(@RequestParam("username") String username, @RequestParam("password") String password) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, admin dashboard has not yet been implemented!");

        User currentUser = dbService.BEContext.User.GetByUsernameAndPassword(username, password);

        if (currentUser == null) {
            mv.addObject("errorMessage", "sorry, you did not input correct credentials!");
        } else {
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
