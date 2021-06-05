package main.java.com.enterprise.sunchip.controllers.login;

import Backend.TripModule;
import Backend.UserModule;
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

    UserModule userModule = new UserModule();

    @RequestMapping(value = "Login", method = RequestMethod.GET)
    public ModelAndView toLoginPage(HttpServletRequest request)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/NormalLogin");
        mv.addObject("userLoggedIn", false);

        try {
            String tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var loggedInUser = userModule.GetByPasswordHash(tokenId);

                mv.addObject("userLoggedIn", true);
                String fullName = loggedInUser.FirstName + " " + loggedInUser.LastName;
                mv.addObject("fullName", fullName);

                switch (loggedInUser.UserClassCode){
                    case 2:
                        return new ModelAndView("redirect:/DriverDashboard");
                    case 3:
                        break;
                }
            }

        } catch (Exception ignore) {}

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
