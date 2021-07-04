package main.Spring.Sunchip.controllers.utlities;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
class MainErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                mv.addObject("errorMessage", statusCode);
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                mv.addObject("errorMessage", statusCode);
            }
        }
        return mv;
    }


    @Override
    public String getErrorPath() {
        return null;
    }
}
