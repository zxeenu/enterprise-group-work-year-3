package main.java.Sunchip.controllers.login;

import Common.Shared;
import Database.Entities.User;
import main.java.Sunchip.services.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AdminLoginController {

    @Autowired
    private LocalSession localSession;

    @RequestMapping(value = "LoginAdmin", method = RequestMethod.GET)
    public ModelAndView toAdminLoginPage(HttpServletRequest request)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/AdminLogin");
        mv.addObject("userLoggedIn", false);

        try {
            String tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var loggedInUser = Shared.BeContext.User.GetByPasswordHash(tokenId);

                mv.addObject("userLoggedIn", true);
                String fullName = loggedInUser.FirstName + " " + loggedInUser.LastName;
                mv.addObject("fullName", fullName);

                if (loggedInUser.UserClassCode == 1) {
                    return new ModelAndView("redirect:/Admin/Dashboard");
                }
            }
        } catch (Exception ignore) {}

        return mv;
    }

    @RequestMapping(value = "LoginAdmin", method = RequestMethod.POST)
    public ModelAndView loginActionForAdmin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        User currentUser = Shared.BeContext.User.GetByUsernameAndPassword(username, password);

        localSession.clearTokenStoredInLocalCashe(request);
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, you did not input correct credentials!");

        if (currentUser != null) {

            switch (currentUser.UserClassCode){
                case 1:
                    localSession.storeTokenInLocalCashe(request, currentUser.HashPassword);
                    return new ModelAndView("redirect:/Admin/Dashboard");
            }
        }
        return mv;
    }

    @RequestMapping(value = "ForgotPasswordAdmin", method = RequestMethod.GET)
    public ModelAndView forgotPasswordActionForAdmin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, forgot password for admin has not been implemented yet!");
        return mv;
    }

    //dbService.BEContext.User.RegisterNewUser("Yas", "Ql", "JonSnow", "Ghost", User.UserType.ADMIN);

}
