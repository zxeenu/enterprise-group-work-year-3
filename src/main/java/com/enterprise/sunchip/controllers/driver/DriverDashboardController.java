package main.java.com.enterprise.sunchip.controllers.driver;

import Backend.TripModule;
import Backend.UserModule;
import Database.Entities.Trip;
import main.java.com.enterprise.sunchip.services.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class DriverDashboardController {

    @Autowired
    private LocalSession localSession;

    UserModule userModule = new UserModule();
    TripModule tripModule = new TripModule();

    @RequestMapping(value = "DriverDashboard", method = RequestMethod.GET)
    public ModelAndView forgotPasswordAction(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String tokenId = "";
        mv.setViewName("pages/dashboard/DriverDashboard");

        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);
            var driver = userModule.GetByPasswordHash(tokenId);
            var tripList = tripModule.GetAllTripsByDriver(driver); // doesnt work for somereason
            mv.addObject("tripList", tripList);
            mv.addObject("driver", driver);

        } catch (Exception ignored) {}

        return mv;
    }

    @RequestMapping(value = "DriverAcceptsJob", method = RequestMethod.POST)
    public ModelAndView driverJobAcceptance(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String tokenId = "";
        mv.setViewName("pages/dashboard/DriverDashboard");

        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);
            var driver = userModule.GetByPasswordHash(tokenId);
            var tripList = tripModule.GetAllTripsByDriver(driver); // doesnt work for somereason
            mv.addObject("tripList", tripList);
            mv.addObject("driver", driver);

        } catch (Exception ignored) {}

        return mv;
    }


}
