package main.java.com.enterprise.sunchip.controllers.login;

import Common.Shared;
import Database.Entities.User;
import main.java.com.enterprise.sunchip.services.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class NormalLoginController {

    @Autowired
    private LocalSession localSession;

    @RequestMapping(value = "Login", method = RequestMethod.GET)
    public ModelAndView toLoginPage()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/NormalLogin");
        System.out.println(mv);
        return mv;
    }

    @RequestMapping(value = "Login", method = RequestMethod.POST)
    public ModelAndView loginActionForAdmin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        User currentUser = Shared.BeContext.User.GetByUsernameAndPassword(username, password);

        localSession.clearTokenStoredInLocalCashe(request);
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, you did not input correct credentials!");

        if (currentUser != null) {

            switch (currentUser.UserClassCode){
                case 2:
                    localSession.storeTokenInLocalCashe(request, currentUser.HashPassword);
                    // return new ModelAndView("forward:/redirectedUrl", model);
                    // return new ModelAndView("redirect:/redirectedUrl", model);
                    return new ModelAndView("redirect:/DriverDashboard");
                case 3:
                    localSession.storeTokenInLocalCashe(request, currentUser.HashPassword);
                    mv.setViewName("pages/error/Error");
                    mv.addObject("errorMessage", "sorry, customer dashboard not implemented!");
                    break;
            }
        }
        return mv;
    }

    @RequestMapping(value = "ForgotPassword", method = RequestMethod.GET)
    public ModelAndView forgotPasswordAction() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/forgot/ForgotPassword");
        return mv;
    }

}
