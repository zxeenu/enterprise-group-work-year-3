package main.java.com.enterprise.sunchip.controllers.login;

import Backend.TripModule;
import Backend.UserModule;
import Common.Shared;
import Database.Entities.Trip;
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
                        return new ModelAndView("redirect:/XAwef32r32jr32oiu"); // admin page
                    case 2:
                        return new ModelAndView("redirect:/DriverDashboard");
                    case 3:
                        break;
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

    @RequestMapping(value = "LoginHello", method = RequestMethod.GET)
    public ModelAndView toLoginPageFromSignup(HttpServletRequest request)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/NormalLogin");
        mv.addObject("userLoggedIn", false);
        return mv;
    }

    @RequestMapping(value = "ForgotPassword", method = RequestMethod.GET)
    public ModelAndView forgotPasswordAction() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/forgot/ForgotPassword");
        return mv;
    }

    @RequestMapping(value = "MakeStuffUp", method = RequestMethod.GET)
    public ModelAndView makeStuffUp() throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/NormalLogin");

//        userModule.RegisterNewUser("bob", "bob", "bob", "bob", User.UserType.ADMIN);
//        userModule.RegisterNewUser("cob", "cob", "cob", "cob", User.UserType.ADMIN);
//        var d = userModule.RegisterNewUser("dob", "dob", "dob", "dob", User.UserType.DRIVER);
//        userModule.RegisterNewUser("eob", "eob", "eob", "eob", User.UserType.DRIVER);
//        userModule.RegisterNewUser("fob", "fob", "fob", "fob", User.UserType.CUSTOMER);
//        userModule.RegisterNewUser("gob", "gob", "gob", "gob", User.UserType.CUSTOMER);
//        userModule.RegisterNewUser("fff", "fff", "fff", "fff", User.UserType.DRIVER);
//        userModule.RegisterNewUser("eee", "eee", "eee", "eee", User.UserType.DRIVER);
//
        var cu = Shared.BeContext.User.RegisterNewUser("fob", "fob", "hht", "hht", User.UserType.DRIVER);

        Trip t = new Trip();
        t.StartName =  "blob";
        t.EndName = "blob";
        t.StartLattitude = 100;
        t.StartLongtitude = 200;
        t.EndLattitude = 240;
        t.EndLongtitude = 40;
        t.Distance = 248;
        Shared.BeContext.Trip.AddTripToQueue(t);

        return mv;
    }

}
