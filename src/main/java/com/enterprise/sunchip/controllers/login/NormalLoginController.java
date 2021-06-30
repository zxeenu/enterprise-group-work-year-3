package main.java.com.enterprise.sunchip.controllers.login;

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

    @RequestMapping(value = "Home", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("redirect:/Login");
    }


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
                        return new ModelAndView("redirect:/AdminDashboard"); // admin page
                    case 2:
                        return new ModelAndView("redirect:/DriverDashboard");
                    case 3:
                        return new ModelAndView("redirect:/LoginHello"); // cleans cookies and takes to fresh page
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
//                    mv.setViewName("pages/error/Error");
//                    mv.setViewName("pages/customers/RequestRide");
//                    mv.addObject("errorMessage", "sorry, customer dashboard not implemented!");
                      return new ModelAndView("redirect:/RequestRide");
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
//        mv.setViewName("pages/customers/RequestRide");
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

    @RequestMapping(value = "MakeStuffUp", method = RequestMethod.GET)
    public ModelAndView makeStuffUp() throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/NormalLogin");

        var driver = Shared.BeContext.User.RegisterNewUser("driver", "driver", "driver", "driver", User.UserType.DRIVER);

        var customer1 = Shared.BeContext.User.RegisterNewUser("fob", "fob", "fob", "fob", User.UserType.CUSTOMER);
        for (Integer i = 0; i < 5; i ++) {
            Trip t = new Trip();
            t.StartName =  "blob" + i.toString();
            t.EndName = "blob" + i.toString();
            t.StartLattitude = 100 * 1;
            t.Customer = customer1;
            t.Driver = driver;
            t.StartLongtitude = 200 * 100 * 1;;
            t.EndLattitude = 240 * 100 * 1;;
            t.EndLongtitude = 40 * 100 * 1;;
            t.Distance = 248 * 100 * 1;;
            Shared.DbContext.Trips.create(t);
        }

        var customer2 = Shared.BeContext.User.RegisterNewUser("bob", "bob", "bob", "bob", User.UserType.CUSTOMER);
        for (Integer i = 0; i < 5; i ++) {
            Trip t = new Trip();
            t.StartName =  "blob" + i.toString();
            t.EndName = "blob" + i.toString();
            t.StartLattitude = 100 * 1;
            t.Customer = customer2;
            t.Driver = driver;
            t.StartLongtitude = 200 * 100 * 1;;
            t.EndLattitude = 240 * 100 * 1;;
            t.EndLongtitude = 40 * 100 * 1;;
            t.Distance = 248 * 100 * 1;;
            Shared.DbContext.Trips.create(t);
        }

        return mv;
    }

}
