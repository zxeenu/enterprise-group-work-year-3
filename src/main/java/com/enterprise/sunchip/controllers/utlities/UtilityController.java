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

    @RequestMapping(value = "Logout", method = RequestMethod.GET)
    public ModelAndView loginActionForAdmin(HttpServletRequest request) {
        localSession.clearTokenStoredInLocalCashe(request);
        return new ModelAndView("redirect:/Login");
    }

//    @RequestMapping("/error")
//    public ModelAndView handleError(HttpServletRequest request) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("pages/error/Error");
//
//        if (status != null) {
//            Integer statusCode = Integer.valueOf(status.toString());
//
//            if(statusCode == HttpStatus.NOT_FOUND.value()) {
//                mv.addObject("errorMessage", statusCode);
//            }
//            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                mv.addObject("errorMessage", statusCode);
//            }
//        }
//        return mv;
//    }

}
