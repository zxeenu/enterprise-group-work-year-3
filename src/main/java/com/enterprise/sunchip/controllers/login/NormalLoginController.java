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
import java.sql.SQLException;

@RestController
public class NormalLoginController {

    @Autowired
    private LocalSession localSession;

    @RequestMapping(value = "Login", method = RequestMethod.GET)
    public ModelAndView toLoginPage(HttpServletRequest request)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/NormalLogin");
        mv.addObject("userLoggedIn", false);

        try {
            String tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var loggedInUser = Shared.BeContext.User.GetByPasswordHash(tokenId);

                mv.addObject("userLoggedIn", true);
                String fullName = loggedInUser.FirstName + " " + loggedInUser.LastName;
                mv.addObject("fullName", fullName);

                switch (loggedInUser.UserClassCode){
                    case 1:
                        return new ModelAndView("redirect:/Admin/Dashboard"); // admin page
                    case 2:
                        return new ModelAndView("redirect:/Driver/Dashboard");  // sends user to driver dashboard
                    case 3:
                        return new ModelAndView("redirect:/Customer/RequestRide"); // sends user to customer request ride page
                }
            }

        } catch (Exception ignore) {
        }

        return mv;
    }

    @RequestMapping(value = "Login", method = RequestMethod.POST)
    public ModelAndView loginActionForAdmin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        User currentUser = Shared.BeContext.User.GetByUsernameAndPassword(username, password);

//        localSession.clearTokenStoredInLocalCashe(request);
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, you did not input correct credentials!");

        if (currentUser != null) {

            switch (currentUser.UserClassCode){
                case 2:
                    localSession.storeTokenInLocalCashe(request, currentUser.HashPassword);
                    // return new ModelAndView("forward:/redirectedUrl", model);
                    // return new ModelAndView("redirect:/redirectedUrl", model);
                    return new ModelAndView("redirect:/Driver/Dashboard");
                case 3:
                    localSession.storeTokenInLocalCashe(request, currentUser.HashPassword);
                    return new ModelAndView("redirect:/Customer/RequestRide");
            }
        }
        return mv;
    }

    @RequestMapping(value = "LoginHello", method = RequestMethod.GET)
    public ModelAndView toLoginPageFromSignup(HttpServletRequest request)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/NormalLogin");
        localSession.clearTokenStoredInLocalCashe(request);
        mv.addObject("userLoggedIn", false);
        return mv;
    }

    @RequestMapping(value = "ForgotPassword", method = RequestMethod.GET)
    public ModelAndView forgotPasswordAction() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/forgot/ForgotPassword");
        return mv;
    }

    /** For debugging purposes the following function lets you visit localhost:8080/MakeStuffUp and easily generate 1 admin 3 drivers and 3 customer accounts **/
    @RequestMapping(value = "MakeStuffUp", method = RequestMethod.GET)
    public ModelAndView makeStuffUp() throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/NormalLogin");

        var admin = Shared.BeContext.User.RegisterNewUser("Admin", "One", "Admin1", "Admin1", User.UserType.ADMIN);
        var user = Shared.BeContext.User.RegisterNewUser("User", "One", "User1", "User1", User.UserType.CUSTOMER);
        var user2 = Shared.BeContext.User.RegisterNewUser("User", "Two", "User2", "User2", User.UserType.CUSTOMER);
        var user3 = Shared.BeContext.User.RegisterNewUser("User", "Three", "User3", "User3", User.UserType.CUSTOMER);
        return mv;
    }

}
