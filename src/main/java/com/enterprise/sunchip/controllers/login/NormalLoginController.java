package main.java.com.enterprise.sunchip.controllers.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NormalLoginController {


    @RequestMapping(value = "Login", method = RequestMethod.GET)
    public ModelAndView toLoginPage()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/NormalLogin");
        System.out.println(mv);
        return mv;
    }

    @RequestMapping(value = "Login", method = RequestMethod.POST)
    public ModelAndView LoginAction() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/NormalLogin");

        // TODO: handle the get data, handle data validation, redirect user

        return mv;

    }

    @RequestMapping(value = "ForgotPassword", method = RequestMethod.GET)
    public ModelAndView forgotPasswordAction() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, forgot password has not been implemented yet!");
        return mv;
    }

}
