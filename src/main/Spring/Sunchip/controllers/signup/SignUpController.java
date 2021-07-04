package main.Spring.Sunchip.controllers.signup;

import main.Common.Shared;
import main.Database.Entities.User;

import main.Spring.Sunchip.models.SignUpModel;
import main.Spring.Sunchip.services.LocalSession;
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
public class SignUpController {

    @Autowired
    private LocalSession localSession;

    @RequestMapping(value = "SignUp", method = RequestMethod.GET)
    public ModelAndView ViewSignUp(HttpServletRequest request)
    {
        try {
            String tokenId = localSession.getTokenStoredInLocalCashe(request);
            if (!tokenId.isEmpty()) {
                return new ModelAndView("redirect:/Login");
            }
        } catch (Exception ignore) { }

        SignUpModel form = new SignUpModel();
        return new ModelAndView("pages/signup/sign-up", "signUpForm", form);
    }

    @RequestMapping(value = "SignUp", method = RequestMethod.POST)
    public ModelAndView SignUpAction(@ModelAttribute("signUpForm") SignUpModel newUser, BindingResult result, HttpServletRequest request)
    {

        User user = null;
        localSession.clearTokenStoredInLocalCashe(request);

        try {
            user = Shared.BeContext.User.RegisterNewUser(
                    newUser.getFirstName(),
                    newUser.getLastName(),
                    newUser.getUsername(),
                    newUser.getPassword(),
                    User.UserType.CUSTOMER);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ModelAndView("redirect:/SignUp");
        }

        return new ModelAndView("redirect:/LoginHello");
    }

}
