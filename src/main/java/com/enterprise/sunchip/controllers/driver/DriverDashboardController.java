package main.java.com.enterprise.sunchip.controllers.driver;

import Backend.TripModule;
import Backend.UserModule;
import Common.Shared;
import main.java.com.enterprise.sunchip.services.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DriverDashboardController {

    @Autowired
    private LocalSession localSession;

    @RequestMapping(value = "DriverDashboard", method = RequestMethod.GET)
    public ModelAndView forgotPasswordAction(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String tokenId = "";
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, you need to login before you can use this page!");

        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var driver = Shared.BeContext.User.GetByPasswordHash(tokenId);
                if (driver != null) {
                    if (driver.UserClassCode == 2) {
                        var tripList = Shared.BeContext.Trip.GetAllTripsByDriver(driver);
                        mv.setViewName("pages/dashboard/DriverDashboard");
                        mv.addObject("tripList", tripList);
                        mv.addObject("driver", driver);
                    }
                }

            }

        } catch (Exception ignored) { }

        return mv;
    }

    /***
     * @param request
     * @return
     */
    @RequestMapping(value = "DriverDashboard", method = RequestMethod.POST)
    public ModelAndView forgotPasswordActionPost(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String tokenId = "";
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, you need to login before you can use this page!");

        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var driver = Shared.BeContext.User.GetByPasswordHash(tokenId);

                if (driver != null) {
                    if (driver.UserClassCode == 2) {
                        var tripList = Shared.BeContext.Trip.GetAllTripsByDriver(driver); // doesnt work for somereason
                        mv.setViewName("pages/dashboard/DriverDashboard");
                        mv.addObject("tripList", tripList);
                        mv.addObject("driver", driver);
                    }
                }
            }

        } catch (Exception ignored) { }

        return mv;
    }

    /***
     * Please do not use the either method = RequestMethod.POST or method = RequestMethod.GET for this method
     * it seems to make the link not work
     *
     * Further investigation will be needed
     *
     * @param request
     * @param tripId
     * @param driverId
     * @return
     */
    @RequestMapping(value = "DriverAcceptsJob")
    public ModelAndView driverJobAcceptance(HttpServletRequest request, @RequestParam("trip_id") String tripId, @RequestParam("driver_id") String driverId) {
        String tokenId = "";
        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var driver = Shared.BeContext.User.GetByPasswordHash(tokenId);

                if (driver != null) {
                    if (driver.UserClassCode == 2) {
                        var tripList = Shared.BeContext.Trip.GetAllTripsByDriver(driver);
                        for (var trip : tripList) {
                            if (trip.ID.toString().equals(tripId)) {
                                // save the accepted state here
                                // save to db
                                Shared.DbContext.Trips.update(trip);
                            }
                        }
                    }
                }
            }


        } catch (Exception e) {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("pages/error/Error");
            mv.addObject("errorMessage", "sorry, the job could not be accepted!");
            return mv;
        }

        return new ModelAndView("redirect:/DriverDashboard");
    }


    @RequestMapping(value = "DriverCompletesJob")
    public ModelAndView driverJobCompletion(HttpServletRequest request, @RequestParam("trip_id") String tripId, @RequestParam("driver_id") String driverId) {
        String tokenId = "";
        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var driver = Shared.BeContext.User.GetByPasswordHash(tokenId);

                if (driver != null) {
                    if (driver.UserClassCode == 2) {
                        var tripList = Shared.BeContext.Trip.GetAllTripsByDriver(driver);
                        for (var trip : tripList)  {
                            if (trip.ID.toString().equals(tripId)) {
                                trip.TripComplete = true;
                                Shared.DbContext.Trips.update(trip);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("pages/error/Error");
            mv.addObject("errorMessage", "sorry, the job could not be completed!");
            return mv;
        }
        return new ModelAndView("redirect:/DriverDashboard");
    }

}
