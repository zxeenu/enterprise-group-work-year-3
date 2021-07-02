package main.java.com.enterprise.sunchip.controllers.admin;

import Common.Shared;
import Database.Entities.Trip;
import main.java.com.enterprise.sunchip.services.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Admin")
public class AdminDashboardController {

    @Autowired
    private LocalSession localSession;

    //  @RequestMapping(value = "AdminDashboard", method = RequestMethod.GET)
    //  @RequestParam("trip_id") String tripId, @RequestParam("driver_id") String driverId
    @GetMapping(path = "/Dashboard")
    public ModelAndView goToAdminDashboard(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, you need to login before you can use this page!");
        String tokenId = "";

        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var admin = Shared.BeContext.User.GetByPasswordHash(tokenId);

                if (admin != null) {
                    if (admin.UserClassCode == 1) {
                        mv.setViewName("pages/dashboard/AdminDashboard");
                        mv.addObject("admin", admin);

                        var tripList = Shared.BeContext.Trip.GetAllTrips();
                        mv.addObject("tripList", tripList);

                        int rejectedJobs = 0;
                        int awaitingPickupJobs = 0;
                        int inProgessJobs = 0;
                        int completedJobs = 0;
                        double dailyTurnOver = 0;

                        for (Trip t : tripList) {
                            switch (t.getState()){
                                case -2:
                                    rejectedJobs++;
                                    break;
                                case 0:
                                    awaitingPickupJobs++;
                                    break;
                                case 1:
                                    inProgessJobs++;
                                    break;
                                case 2:
                                    completedJobs++;
                                    break;
                            }
                            dailyTurnOver += t.PaidAmount;
                        }
                        mv.addObject("driverName", "All");
                        mv.addObject("rejectedJobs", rejectedJobs);
                        mv.addObject("awaitingPickupJobs", awaitingPickupJobs);
                        mv.addObject("inProgessJobs", inProgessJobs);
                        mv.addObject("completedJobs", completedJobs);
                        mv.addObject("dailyTurnOver", dailyTurnOver);
                    }
                }
            }

        } catch (Exception e) {
            localSession.clearTokenStoredInLocalCashe(request);
        }
        return mv;
    }

//    @RequestMapping(value = "AdminDashboardDrivers", method = RequestMethod.GET)
    @GetMapping(path = "/Drivers")
    public ModelAndView goToAdminDriversView(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, you need to login before you can use this page!");

        String tokenId = "";

        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var admin = Shared.BeContext.User.GetByPasswordHash(tokenId);

                if (admin != null) {
                    if (admin.UserClassCode == 1) {
                        mv.setViewName("pages/admin/DriverDetails");

                        var dirverList = Shared.BeContext.User.GetAllDrivers();
                        mv.addObject("driverList", dirverList);
                    }
                }
            }

        } catch (Exception e) {
            localSession.clearTokenStoredInLocalCashe(request);
        }
        return mv;
    }

//    @RequestMapping(value = "AdminDashboardCustomers", method = RequestMethod.GET)
    @GetMapping(path = "/Customers")
    public ModelAndView goToAdminCustomersView(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, you need to login before you can use this page!");

        String tokenId = "";

        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var admin = Shared.BeContext.User.GetByPasswordHash(tokenId);

                if (admin != null) {
                    if (admin.UserClassCode == 1) {
                        mv.setViewName("pages/admin/CustomerDetails");
                        var customerList = Shared.BeContext.User.GetAllCustomers();
                        mv.addObject("customerList", customerList);
                    }
                }
            }

        } catch (Exception e) {
            localSession.clearTokenStoredInLocalCashe(request);
        }
        return mv;
    }


}
