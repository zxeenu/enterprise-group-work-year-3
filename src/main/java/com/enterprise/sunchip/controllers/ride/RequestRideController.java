package main.java.com.enterprise.sunchip.controllers.ride;

import Common.Shared;
import Database.Entities.Trip;
import main.java.com.enterprise.sunchip.models.RequestModel;
import main.java.com.enterprise.sunchip.services.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;

@RestController
public class RequestRideController {
    @Autowired
    private LocalSession localSession;

    @RequestMapping(value = "RequestRide", method = RequestMethod.GET)
    public ModelAndView ViewRequestRide(HttpServletRequest request)
    {
        RequestModel form = new RequestModel();
        return new ModelAndView("pages/requestride/request-ride", "requestForm", form);
    }

    @RequestMapping(value = "RequestRide", method = RequestMethod.POST)
    public ModelAndView RequestAction(@ModelAttribute("requestForm") RequestModel newRequest, BindingResult result, HttpServletRequest request)
    {
        try {
            Trip newtrip = new Trip();
            String tokenId = "";

            tokenId = localSession.getTokenStoredInLocalCashe(request);
            if (!tokenId.isEmpty()) {
                var customer = Shared.BeContext.User.GetByPasswordHash(tokenId);
                if (customer != null){
                    newtrip.setCustomer(customer);
                    newtrip.setBookingDate(newRequest.getDateAndTime());
                    newtrip.setStartName(newRequest.getLocation());
                    newtrip.setEndName(newRequest.getDestination());
                    Shared.BeContext.Trip.AssignTripToAvailableDriver(newtrip);
                    Shared.DbContext.Trips.create(newtrip);

                }

            }
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
            return new ModelAndView("redirect:/RequestRide");
        }
        return new ModelAndView("redirect:/RequestRide");
    }
}

