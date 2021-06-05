package main.java.com.enterprise.sunchip.controllers.signup;

import Backend.UserModule;
import Database.Entities.User;
import Database.Entities.Vehicle;
import main.java.com.enterprise.sunchip.models.SignUpModel;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@RestController
public class SignUpController {

    @RequestMapping(value = "SignUp", method = RequestMethod.GET)
    public ModelAndView ViewSignUp()
    {
        SignUpModel form = new SignUpModel();
        return new ModelAndView("pages/signup/sign-up", "signUpForm", form);
    }

    @RequestMapping(value = "SignUp", method = RequestMethod.POST)
    public String SignUpAction(@ModelAttribute("signUpForm") SignUpModel newUser)
    {
        UserModule userModule = new UserModule();
        try {
            if (newUser.getUserType() == User.UserType.DRIVER)
            {
                User user = userModule.RegisterNewUser(
                        newUser.getFirstName(),
                        newUser.getLastName(),
                        newUser.getUsername(),
                        newUser.getPassword(),
                        User.UserType.DRIVER);

                user.PrimaryVehicle = new Vehicle(
                        newUser.getManufacturer(),
                        newUser.getLicensePlateNo(),
                        newUser.getColor());
            }
            else
            {
                userModule.RegisterNewUser(
                        newUser.getFirstName(),
                        newUser.getLastName(),
                        newUser.getUsername(),
                        newUser.getPassword(),
                        User.UserType.CUSTOMER);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "NormalLogin";
    }

}
