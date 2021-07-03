package main.java.com.enterprise.sunchip.controllers.ride;

import Common.Shared;
import Database.Entities.Trip;
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

@RestController
@RequestMapping("/Customer")
public class RequestRideController {
    @Autowired
    private LocalSession localSession;

//    @RequestMapping(value = "RequestRide", method = RequestMethod.GET)
    @GetMapping(path = "/RequestRide")
    public ModelAndView ViewRequestRide(HttpServletRequest request)
    {
        try {
            String tokenId = "";
            tokenId = localSession.getTokenStoredInLocalCashe(request);
            if (!tokenId.isEmpty()) {
                var customer = Shared.BeContext.User.GetByPasswordHash(tokenId);
                // Ziaan, if trip exists for user, dont let them create another one, redirect to confirmed page
                var tripExists = Shared.BeContext.Trip.GetNotCompleteTripsByCustomer(customer);
                if (!tripExists.isEmpty()) {
                    System.out.println("------- " + tripExists.size());
                    return new ModelAndView("redirect:/Customer/ComfirmedRide");
                }
            }
        } catch (Exception e) {
            // do nothing
        }
        RequestModel form = new RequestModel();
        return new ModelAndView("pages/requestride/request-ride", "requestForm", form);
    }

//    @RequestMapping(value = "RequestRide", method = RequestMethod.POST)
    @PostMapping(path = "/RequestRide")
    public ModelAndView RequestAction(@ModelAttribute("requestForm") RequestModel newRequest, BindingResult result, HttpServletRequest request)
    {

        try {
            String tokenId = "";

            tokenId = localSession.getTokenStoredInLocalCashe(request);
            if (!tokenId.isEmpty()) {
                var customer = Shared.BeContext.User.GetByPasswordHash(tokenId);
                if (customer != null){
                    Trip newtrip = Shared.BeContext.Trip.RequestNewTrip(customer);
                    newtrip.setCreationTime(newRequest.getDateAndTime());
                    newtrip.setStartName(newRequest.getStartLocationNameFromWebservice());
                    newtrip.setEndName(newRequest.getDestinationNameFromWebservice());
                    newtrip.setEndLattitude(newRequest.getDestinationCoordinatesFromWebservice()[0]);
                    newtrip.setEndLongtitude(newRequest.getDestinationCoordinatesFromWebservice()[1]);
                    newtrip.setStartLattitude(newRequest.getStartLocationCoordinatesFromWebservice()[0]);
                    newtrip.setStartLongtitude(newRequest.getStartLocationCoordinatesFromWebservice()[1]);
                    newtrip.setPaidAmount(100*2);
//                    Shared.BeContext.Trip.AssignTripToAvailableDriver(newtrip);
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
//            return new ModelAndView("redirect:/Customer/RequestRide");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/Customer/ComfirmedRide");
    }
}

