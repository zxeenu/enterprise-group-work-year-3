package main.java.com.enterprise.sunchip.controllers.driver;

import Common.Shared;
import Database.Entities.User;
import Database.Entities.Vehicle;
import main.java.com.enterprise.sunchip.models.DriverModel;
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

@RestController
public class AddDriverController {

    @Autowired
    private LocalSession localSession;

    @RequestMapping(value = "/Admin/AddDriver", method = RequestMethod.GET)
    public ModelAndView ViewAddDriver(HttpServletRequest request)
    {
        DriverModel form = new DriverModel();
        return new ModelAndView("pages/adddriver/add-driver", "driverForm", form);
    }

    @RequestMapping(value = "/Admin/AddDriver", method = RequestMethod.POST)
    public ModelAndView SignUpAction(@ModelAttribute("driverForm") DriverModel newDriver, BindingResult result, HttpServletRequest request)
    {
//        if (result.hasErrors()){
//            return "Error";
//        }

        User user = null;
        Vehicle vehicle = new Vehicle(newDriver.getManufacturer(),
                                      newDriver.getLicensePlateNo(),
                                      newDriver.getColour());

        localSession.clearTokenStoredInLocalCashe(request);
        try {
            user = Shared.BeContext.User.RegisterNewUser(
                    newDriver.getFirstName(),
                    newDriver.getLastName(),
                    newDriver.getUsername(),
                    newDriver.getPassword(),
                    User.UserType.DRIVER);
            Shared.DbContext.Vehicles.create(vehicle);
            user.PrimaryVehicle = vehicle;
            Shared.BeContext.User.UpdateUser(user);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ModelAndView("redirect:/SignUp");
        }



        return new ModelAndView("redirect:/SignUp");
    }

}
