package main.Spring.Sunchip.controllers.admin;

import main.Common.Logger;
import main.Common.Shared;
import main.Database.Entities.Trip;
import main.Spring.Sunchip.services.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

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

                        var driverListDropDown = Shared.BeContext.User.GetAllDrivers();
                        mv.addObject("driverList", driverListDropDown);
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
//            localSession.clearTokenStoredInLocalCashe(request);
        }
        return mv;
    }

//    @PostMapping(value = "/Filter")
//    public ModelAndView goToAdminDashboard(HttpServletRequest request, @RequestParam("driver") String driverId, @RequestParam("status") String statusCode, @RequestParam("history-start") String historyStart, @RequestParam("history-end") String historyEnd) {
//        ModelAndView mv = new ModelAndView();
//        String tokenId = "";
//
//        try {
//            tokenId = localSession.getTokenStoredInLocalCashe(request);
//
//            if (!tokenId.isEmpty()) {
//                var admin = Shared.BeContext.User.GetByPasswordHash(tokenId);
//
//                if (admin != null) {
//                    if (admin.UserClassCode == 1) {
//                        mv.setViewName("pages/dashboard/AdminDashboard");
//                        String driverName = "All";
//
//                        // date filtering
//                        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
//                        Date end = null;
//                        Date start = null;
//                        if (historyStart.isEmpty()) {
//                            start = sdformat.parse("2000-1-1");
//                        }
//                        if (historyEnd.isEmpty()) {
//                            end = sdformat.parse("3000-1-1");
//                        }
//
//                        // var tripList = Shared.BeContext.Trip.GetAllTrips();
//                        var tripListDate = Shared.BeContext.Trip.GetTripWithinDateRange(start, end);
//                        List<Trip> tripList = new ArrayList<>();
//
//                        // filter trips by driver or all drivers
//                        if (!driverId.contentEquals("all_drivers")) {
//
//                            var driverList = Shared.BeContext.User.GetAllDrivers();
//                            for (var driver : driverList) {
//                                if (driverId.contentEquals(driver.ID.toString())) {
//                                    tripList = Shared.BeContext.Trip.FilterByDriver(tripListDate, driver);
//                                    driverName = driver.FirstName + " " + driver.LastName;
//                                }
//                            }
//                        }
//
//                        var preparedTripListOngoing = new ArrayList<Trip>();
//                        var preparedTripListRejected = new ArrayList<Trip>();
//                        var preparedTripListCompleted = new ArrayList<Trip>();
//                        var preparedTripListAll= new ArrayList<Trip>();
//
//                        for (Trip t : tripList) {
//                            switch (t.getState()) {
//                                case 1:
//                                    preparedTripListOngoing.add(t);
//                                    break;
//                                case -2:
//                                    preparedTripListRejected.add(t);
//                                    break;
//                                case 2:
//                                    preparedTripListCompleted.add(t);
//                                    break;
//                            }
//
//                            preparedTripListAll.add(t);
//                        }
//
//
//                        int rejectedJobs = 0;
//                        int awaitingPickupJobs = 0;
//                        int inProgessJobs = 0;
//                        int completedJobs = 0;
//                        double dailyTurnOver = 0;
//
//                        for (Trip t : preparedTripListAll) {
//                            switch (t.getState()){
//                                case -2:
//                                    rejectedJobs++;
//                                    break;
//                                case 0:
//                                    awaitingPickupJobs++;
//                                    break;
//                                case 1:
//                                    inProgessJobs++;
//                                    break;
//                                case 2:
//                                    completedJobs++;
//                                    break;
//                            }
//                            dailyTurnOver += t.PaidAmount;
//                        }
//
//                        var driverListDropDown = Shared.BeContext.User.GetAllDrivers();
//                        mv.addObject("driverList", driverListDropDown);
//                        mv.addObject("driverName", driverName);
//                        mv.addObject("rejectedJobs", rejectedJobs);
//                        mv.addObject("awaitingPickupJobs", awaitingPickupJobs);
//                        mv.addObject("inProgessJobs", inProgessJobs);
//                        mv.addObject("completedJobs", completedJobs);
//                        mv.addObject("dailyTurnOver", dailyTurnOver);
//
//                        switch (statusCode) {
//                            case "ongoing":
//                                mv.addObject("tripList", preparedTripListOngoing);
//                                break;
//                            case "rejected":
//                                mv.addObject("tripList", preparedTripListRejected);
//                                break;
//                            case "completed":
//                                mv.addObject("tripList", preparedTripListCompleted);
//                                break;
//                            case "all_status":
//                                mv.addObject("tripList", preparedTripListAll);
//                                break;
//                        }
//                    }
//                }
//            }
//
//        } catch (Exception e) {
////            localSession.clearTokenStoredInLocalCashe(request);
//        }
//        return mv;
//    }

    @PostMapping(value = "/Filter")
    public ModelAndView goToAdminDashboard(HttpServletRequest request, @RequestParam("driver") String driverId, @RequestParam("status") String statusCode, @RequestParam("history-start") String historyStart, @RequestParam("history-end") String historyEnd) {
        ModelAndView mv = new ModelAndView();
        String tokenId = "";

        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var admin = Shared.BeContext.User.GetByPasswordHash(tokenId);

                if (admin != null) {
                    if (admin.UserClassCode == 1) {
                        mv.setViewName("pages/dashboard/AdminDashboard");
                        String driverName = "All";

                        // date filtering
                        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                        Date start = null;
                        Date end = null;

                        if (historyStart.isBlank()) {
                            start = sdformat.parse("2000-1-1");
                        } else {
                            start = sdformat.parse(historyStart);
                        }

                        if (historyEnd.isBlank()) {
                            end = sdformat.parse("3000-1-1");
                        } else {
                            end = sdformat.parse(historyEnd);
                        }

                        var driverList = Shared.BeContext.User.GetAllDrivers();
                        var tripListDateDriverFiltered = new ArrayList<Trip>();
                        String driverSelected = "";

                        // get the driver object for filering
                        if (!driverId.contentEquals("all_drivers")) {
                            for (var driver : driverList) {
                                if (driverId.contentEquals(driver.ID.toString())) {
                                    driverSelected = driver.ID.toString();
                                    driverName = driver.FirstName + " " + driver.LastName;
                                }
                            }
                        }


                        var tripListDateTemp = Shared.BeContext.Trip.GetTripWithinDateRange(start, end);
                        if (!driverSelected.isEmpty()) {
                            for (var t : tripListDateTemp) {
                                    if (t.getDriver() != null) {
                                        if (driverSelected.contentEquals(Integer.toString(t.Driver.ID))) {
                                            String msg = t.ID.toString();
                                            Logger.Instance.Add(msg, Logger.LogLevels.INFO);
                                            tripListDateDriverFiltered.add(t);
                                        }
                                    }
                            }
                            } else {
                                tripListDateDriverFiltered.addAll(tripListDateTemp);
                            }

                        var preparedTripListOngoing = new ArrayList<Trip>();
                        var preparedTripListRejected = new ArrayList<Trip>();
                        var preparedTripListCompleted = new ArrayList<Trip>();

                        for (Trip t : tripListDateDriverFiltered) {
                            switch (t.getState()) {
                                case 1:
                                    preparedTripListOngoing.add(t);
                                    break;
                                case -2:
                                    preparedTripListRejected.add(t);
                                    break;
                                case 2:
                                    preparedTripListCompleted.add(t);
                                    break;
                            }
                        }


                        int rejectedJobs = 0;
                        int awaitingPickupJobs = 0;
                        int inProgessJobs = 0;
                        int completedJobs = 0;
                        double dailyTurnOver = 0;
                        for (Trip t : tripListDateDriverFiltered) {
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
                                    dailyTurnOver += t.PaidAmount;
                                    break;
                            }
                        }
                        switch (statusCode) {
                            case "ongoing":
                                mv.addObject("tripList", preparedTripListOngoing);
                                break;
                            case "rejected":
                                mv.addObject("tripList", preparedTripListRejected);
                                break;
                            case "completed":
                                mv.addObject("tripList", preparedTripListCompleted);
                                break;
                            case "all_status":
                                mv.addObject("tripList", tripListDateDriverFiltered);
                                break;
                        }

                        mv.addObject("driverList", driverList);
                        mv.addObject("driverName", driverName);
                        mv.addObject("rejectedJobs", rejectedJobs);
                        mv.addObject("awaitingPickupJobs", awaitingPickupJobs);
                        mv.addObject("inProgessJobs", inProgessJobs);
                        mv.addObject("completedJobs", completedJobs);
                        mv.addObject("dailyTurnOver", dailyTurnOver);
                    }
                }
            }

        } catch (Exception e) {
//            localSession.clearTokenStoredInLocalCashe(request);
            System.out.println(e);
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
//            localSession.clearTokenStoredInLocalCashe(request);
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
//            localSession.clearTokenStoredInLocalCashe(request);
        }
        return mv;
    }

    @GetMapping(path = "/DriverDelete")
    public ModelAndView deleteDriver(HttpServletRequest request, @RequestParam("driver_id") String driverId) {
        String tokenId = "";
        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var admin = Shared.BeContext.User.GetByPasswordHash(tokenId);

                if (admin != null) {
                    if (admin.UserClassCode == 1) {
                        var driverList = Shared.BeContext.User.GetAllDrivers();
                        for (var driver : driverList) {
                            if (driver.ID.toString().equals(driverId)) {
                                Shared.BeContext.User.DeleteUser(driver);
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
        return new ModelAndView("redirect:/Admin/Drivers");
    }


    @GetMapping(path = "/CustomerDelete")
    public ModelAndView deleteCustomer(HttpServletRequest request, @RequestParam("customer_id") String customerId) {
        String tokenId = "";
        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var admin = Shared.BeContext.User.GetByPasswordHash(tokenId);

                if (admin != null) {
                    if (admin.UserClassCode == 1) {
                        var customerList = Shared.BeContext.User.GetAllCustomers();
                        for (var customer : customerList) {
                            if (customer.ID.toString().equals(customerId)) {
                                Shared.BeContext.User.DeleteUser(customer);
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
        return new ModelAndView("redirect:/Admin/Customers");
//        return new ModelAndView("redirect:/Admin/Dashboard");
    }


}
