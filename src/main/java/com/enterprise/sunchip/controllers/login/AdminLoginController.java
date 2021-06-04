package com.enterprise.sunchip.controllers.login;

import Database.Entities.User;
import WebUI.Session;
import com.enterprise.sunchip.models.WebUIManager;
import com.enterprise.sunchip.services.DbService;
import com.enterprise.sunchip.services.LocalSession;
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
    private DbService dbService;

    @Autowired
    private LocalSession localSession;

    private static Session userSession = new Session();


    private static WebUIManager webesiteUIManager = new WebUIManager();

    @RequestMapping(value = "LoginAdmin", method = RequestMethod.GET)
    public ModelAndView toLoginPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/AdminLogin");
        return mv;

    }

    @RequestMapping(value = "LoginAdmin", method = RequestMethod.POST)
    public ModelAndView loginAction(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        User currentUser = dbService.BEContext.User.GetByUsernameAndPassword(username, password);

        if (currentUser == null) {
            localSession.storeTokenInLocalCashe(request, "");
            mv.addObject("errorMessage", "sorry, you did not input correct credentials!");
        } else {
            localSession.storeTokenInLocalCashe(request, currentUser.HashUsername);

            webesiteUIManager.setTokenId(currentUser.HashUsername);
            userSession.Subscribe(webesiteUIManager);
            userSession.StartSession();

            mv.addObject("errorMessage", "sorry, successful login. But admin page has not been implemented!");
        }
        return mv;
    }

    @RequestMapping(value = "ForgotPasswordAdmin", method = RequestMethod.GET)
    public ModelAndView forgotPassword() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, forgot password has not been implemented yet!");
        return mv;
    }

    @RequestMapping(value = "Check", method = RequestMethod.GET)
    public ModelAndView checkSession() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        System.out.println("--------------" + webesiteUIManager.HasSessionExpired);
        mv.addObject("errorMessage", webesiteUIManager.HasSessionExpired);
        return mv;
    }

    //dbService.BEContext.User.RegisterNewUser("Yas", "Ql", "JonSnow", "Ghost", User.UserType.ADMIN);

}
