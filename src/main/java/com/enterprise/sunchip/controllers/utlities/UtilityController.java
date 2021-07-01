package main.java.com.enterprise.sunchip.controllers.utlities;

import main.java.com.enterprise.sunchip.services.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UtilityController {

    @Autowired
    private LocalSession localSession;

    @RequestMapping(value="/**/Logout", method=RequestMethod.GET)
    public ModelAndView logoutActionForAll(HttpServletRequest request) {
        localSession.clearTokenStoredInLocalCashe(request);
        return new ModelAndView("redirect:/Login");
    }

    @RequestMapping(value = "Logout", method = RequestMethod.GET)
    public ModelAndView logoutAction(HttpServletRequest request) {
        localSession.clearTokenStoredInLocalCashe(request);
        return new ModelAndView("redirect:/Login");
    }


}
