package main.java.com.enterprise.sunchip.controllers.login;

import Common.Shared;
import Database.Entities.User;
import main.java.com.enterprise.sunchip.services.LocalSession;
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

//    private static Session userSession = new Session();
//    private static WebUIManager webesiteUIManager = new WebUIManager();

    @RequestMapping(value = "LoginAdmin", method = RequestMethod.GET)
    public ModelAndView toAdminLoginPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/AdminLogin");
        return mv;

    }

    @RequestMapping(value = "LoginAdmin", method = RequestMethod.POST)
    public ModelAndView loginActionForAdmin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        User currentUser = Shared.BeContext.User.GetByUsernameAndPassword(username, password);

        if (currentUser == null) {
            localSession.storeTokenInLocalCashe(request, "");
            mv.addObject("errorMessage", "sorry, you did not input correct credentials!");
        } else {
            localSession.storeTokenInLocalCashe(request, currentUser.HashUsername);

//            webesiteUIManager.setTokenId(currentUser.HashUsername);
//            userSession.Subscribe(webesiteUIManager);
//            userSession.StartSession();

            mv.addObject("errorMessage", "sorry, successful login. But admin page has not been implemented!");
        }
        return mv;
    }

    @RequestMapping(value = "ForgotPasswordAdmin", method = RequestMethod.GET)
    public ModelAndView forgotPasswordActionForAdmin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, forgot password has not been implemented yet!");
        return mv;
    }

    @RequestMapping(value = "Check", method = RequestMethod.GET)
    public ModelAndView checkSessionActionForAdmin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
//        System.out.println("--------------" + webesiteUIManager.HasSessionExpired);
//        mv.addObject("errorMessage", webesiteUIManager.HasSessionExpired);
        return mv;
    }

    //dbService.BEContext.User.RegisterNewUser("Yas", "Ql", "JonSnow", "Ghost", User.UserType.ADMIN);

}
