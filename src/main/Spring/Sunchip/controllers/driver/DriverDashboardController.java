package main.Spring.Sunchip.controllers.driver;

import main.Common.Shared;
import main.Database.Entities.Trip;
import main.Database.Entities.TripRejectionReason;
import main.Database.Entities.User;
//import main.java.com.enterprise.sunchip.services.LocalSession;
import main.Spring.Sunchip.services.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/Driver")
public class DriverDashboardController {

    @Autowired
    private LocalSession localSession;

//    @RequestMapping(value = "DriverDashboard", method = RequestMethod.GET)
    @GetMapping(path = "/Dashboard")
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
//    @RequestMapping(value = "DriverDashboard", method = RequestMethod.POST)
    @PostMapping(path = "/Dashboard")
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
    @GetMapping(path = "/DriverAcceptsJob")
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
//                                Shared.BeContext.Trip.AssignTripDriver(trip, driver);
                                trip.Driver = driver;
                                trip.State = Trip.TripState.IN_PROGRESS;
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

        return new ModelAndView("redirect:/Driver/Dashboard");
    }


//    @RequestMapping(value = "DriverCompletesJob")
    @GetMapping(path = "/DriverCompletesJob")
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
//                                trip.TripComplete = true;
//                                trip.setState(2); // completion code
//                                Shared.DbContext.Trips.update(trip);
//                                Shared.BeContext.Trip.MarkTripAsComplete(trip);

                                trip.State = Trip.TripState.COMPLETE;
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
        return new ModelAndView("redirect:/Driver/Dashboard");
    }

//    @RequestMapping(value = "DriverRejectsJob")
    @GetMapping(path = "/DriverRejectsJob")
    public ModelAndView driverJobRejection(HttpServletRequest request, @RequestParam("trip_id") String tripId, @RequestParam("driver_id") String driverId) {
        String tokenId = "";
        ModelAndView mv = new ModelAndView();
        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var driver = Shared.BeContext.User.GetByPasswordHash(tokenId);

                if (driver != null) {
                    if (driver.UserClassCode == 2) {
                        var tripList = Shared.BeContext.Trip.GetAllTripsByDriver(driver);
                        for (var trip : tripList)  {
                            if (trip.ID.toString().equals(tripId)) {
                                HttpSession session = request.getSession();
                                session.setAttribute("tripToCancell", trip);
                                session.setAttribute("driverAssosicatedToCancelledJob", driver);
                                mv.setViewName("pages/jobrejection/DriverJobRejection");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            mv.setViewName("pages/error/Error");
            mv.addObject("errorMessage", "sorry, the job could not be completed!");
            return mv;
        }
        return mv;
    }

    @PostMapping(path = "/DriverRejectsJob")
    public ModelAndView driverJobRejectionConfirmation(HttpServletRequest request, @RequestParam("rejection_reason") String rejectionReason){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();

        try {

            var trip = (Trip) session.getAttribute("tripToCancell");
            var driver = (User) session.getAttribute("driverAssosicatedToCancelledJob");

            Shared.BeContext.Trip.RejectByDriver(trip, driver, rejectionReason);

            mv.setViewName("pages/dashboard/DriverDashboard");

        } catch (Exception e) {
            mv.setViewName("pages/error/Error");
            mv.addObject("errorMessage", "sorry, the job could not be completed! Something went wrong");
            return mv;
        }

        // remove the meta data
        session.setAttribute("tripToCancell", null);
        session.setAttribute("driverAssosicatedToCancelledJob", null);

        return new ModelAndView("redirect:/Driver/Login");
    }

}
