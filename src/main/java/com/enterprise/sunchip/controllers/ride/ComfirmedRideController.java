package main.java.com.enterprise.sunchip.controllers.ride;

import Common.Shared;
import Database.Entities.Trip;
import Database.Entities.User;
import main.java.com.enterprise.sunchip.services.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Customer")
public class ComfirmedRideController {
    @Autowired
    private LocalSession localSession;

//    @RequestMapping(value = "ComfirmedRide", method = RequestMethod.GET)
    @GetMapping(path = "/ComfirmedRide")
    public ModelAndView ViewComfirmedRide(HttpServletRequest request)
    {
        ModelAndView mv = new ModelAndView();
        String tokenId = "";

        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);
            if (!tokenId.isEmpty()) {
                var customer = Shared.BeContext.User.GetByPasswordHash(tokenId);
                if (customer != null) {
                    if (customer.UserClassCode == 3) {
                        var activeTrip = Shared.BeContext.Trip.GetNotCompleteTripsByCustomer(customer);
                        Trip trip = activeTrip.get(0);
                        User driver = trip.getDriver();
                        mv.setViewName("pages/comfirmedride/comfirmed-ride");
                        mv.addObject("trip", trip);
                        mv.addObject("driver", driver);
                    }
                }

            }

        } catch (Exception ignored) { }

        return mv;
    }

//    @RequestMapping(value = "CancelRide", method = RequestMethod.GET)
    @GetMapping(path = "/CancelRide")
    public ModelAndView CancelRide(HttpServletRequest request)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        String tokenId = "";
        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var customer = Shared.BeContext.User.GetByPasswordHash(tokenId);
                if (customer != null) {
                    if (customer.UserClassCode == 3) {
                        var activeTrip = Shared.BeContext.Trip.GetNotCompleteTripsByCustomer(customer);
                        Trip trip = activeTrip.get(0);
                        Shared.DbContext.Trips.delete(trip);
                        mv.setViewName("pages/comfirmedride/comfirmed-ride");
                    }
                }

            }

        } catch (Exception ignored) { }

        return mv;
    }
}
