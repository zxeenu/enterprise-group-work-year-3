package main.java.com.enterprise.sunchip.controllers.ride;

import Common.Logger;
import Common.Shared;
import Database.Entities.Trip;
import Tests.Const;
import com.fasterxml.jackson.core.JsonProcessingException;
import main.java.com.enterprise.sunchip.models.RequestModel;
import main.java.com.enterprise.sunchip.services.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@RestController
@RequestMapping("/Customer")
public class RequestRideController {
    @Autowired
    private LocalSession localSession;

    @GetMapping(path = "/RequestRide")
    public ModelAndView ViewRequestRide(HttpServletRequest request)
    {
        try {
            String tokenId = "";
            tokenId = localSession.getTokenStoredInLocalCashe(request);
            if (!tokenId.isEmpty()) {
                var customer = Shared.BeContext.User.GetByPasswordHash(tokenId);
                // Ziaan, if trip exists for user, dont let them create another one, redirect to confirmed page

                boolean foundTrue = false;
                var tripExists_ = Shared.BeContext.Trip.GetAllTrips();
                for (Trip t: tripExists_) {
                    if (t.Customer != null) {
                        if (t.Customer.ID.equals(customer.ID)) {
                            if (!t.TripComplete) {
                                foundTrue = true;
                            }
                        }
                    }
                }

                if (foundTrue) {

                    return new ModelAndView("redirect:/Customer/ComfirmedRide");
                }
            }
        } catch (Exception e) {
            // do nothing
        }

        RequestModel form = new RequestModel();
        return new ModelAndView("pages/requestride/request-ride", "requestForm", form);
    }

    @PostMapping(path = "/RequestRide")
    public ModelAndView RequestAction(@ModelAttribute("requestForm") RequestModel newRequest, BindingResult result, HttpServletRequest request)
    {

        try {
            String tokenId = "";

            tokenId = localSession.getTokenStoredInLocalCashe(request);
            if (!tokenId.isEmpty()) {
                var customer = Shared.BeContext.User.GetByPasswordHash(tokenId);
                if (customer != null){
//                    Trip newtrip = Shared.BeContext.Trip.RequestNewTrip(customer);


//                    var matching_trips = new ArrayList<Trip>();
//                    for (Trip ct : Shared.DbContext.Trips) {
//                        if (ct.Driver != null) {
//                            if ((ct.Customer.ID.equals(customer.ID)) && (!ct.TripComplete)) matching_trips.add(ct);
//                        }
//                    }
//
//                    if (matching_trips.size() > 0) {
//                        ModelAndView mv = new ModelAndView();
//                        mv.setViewName("pages/error/Error");
//                        mv.addObject("errorMessage", "You already have a request!");
//                        return mv;
//                    }

                    var newtrip = new Trip();
                    newtrip.TripComplete = false;
                    newtrip.Customer = customer;
                    Shared.DbContext.Trips.create(newtrip);

                    newtrip.setCreationTime(newRequest.getDateAndTime());
                    newtrip.setStartName(newRequest.getStartLocationNameFromWebservice());
                    newtrip.setEndName(newRequest.getDestinationNameFromWebservice());
                    newtrip.setEndLattitude(newRequest.getDestinationCoordinatesFromWebservice()[0]);
                    newtrip.setEndLongtitude(newRequest.getDestinationCoordinatesFromWebservice()[1]);
                    newtrip.setStartLattitude(newRequest.getStartLocationCoordinatesFromWebservice()[0]);
                    newtrip.setStartLongtitude(newRequest.getStartLocationCoordinatesFromWebservice()[1]);

                    var handler = new Backend.Maps.OpenRouteService.ORSHandler("5b3ce3597851110001cf624856dfbc93ad4f41428588775eb447549b");
                    var location1= handler.Search(newRequest.getDestinationNameFromWebservice()).get(0);
                    var location2 = handler.Search(newRequest.getStartLocationNameFromWebservice()).get(0);

                    var partOne = Math.pow((location2.Coordinates.Lattitude - location1.Coordinates.Lattitude), 2);
                    var partTwo = Math.pow((location2.Coordinates.Longtitude - location1.Coordinates.Longtitude), 2);
                    var distance = Math.sqrt(partOne+ partTwo);
//                    var route = handler.GetRoutes(location1, location2).get(0);
                    double rate = 0.1;
//                    double actualAmount = route.Distance*rate;
                    double actualAmount = distance*rate;
                    double amountOwed = (double)Math.round(actualAmount * 100.0) / 100.0;

                    if (amountOwed > 10) {
                        amountOwed = 10;
                    }

//                    newtrip.setDistance(route.Distance);
                    newtrip.setDistance(distance);
                    newtrip.setPaidAmount((float)amountOwed);

                    var driverAssigned = Shared.BeContext.Trip.AssignTripToAvailableDriverForSelection(newtrip);
                    Logger.Instance.Add(driverAssigned.ID.toString() + "<<<", Logger.LogLevels.INFO);
                    if (driverAssigned == null) {
                        Shared.BeContext.Trip.AddTripToPool(newtrip);
                    }
                    Shared.DbContext.Trips.update(newtrip);

                }

            }
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
            // Ziaan, added this to redirect to an error page.
            ModelAndView mv = new ModelAndView();
            mv.setViewName("pages/error/Error");
            mv.addObject("errorMessage", "Sorry, you're request could not be processed!");
            return mv;
        } catch (JsonProcessingException e) {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("pages/error/Error");
            mv.addObject("errorMessage", "Sorry, you're request could not be processed!");
        }

        return new ModelAndView("redirect:/Customer/ComfirmedRide");
    }
}

