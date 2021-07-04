package Webapp.java.Sunchip.controllers.home;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homeView()
    { return new ModelAndView("redirect:/Login"); }

    @RequestMapping(value="/**/Home", method=RequestMethod.GET)
    public ModelAndView homeViewForAll()
    { return new ModelAndView("redirect:/Login"); }

    @RequestMapping(value="/**/Login", method=RequestMethod.GET)
    public ModelAndView LoginViewForAll()
    { return new ModelAndView("redirect:/Login"); }

    @RequestMapping(value = "Home", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("redirect:/Login");
    }

}
