package main.java.com.enterprise.sunchip.controllers.home;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homeView()
    {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("pages/login/NormalLogin");
//        System.out.println(mv);
//        return mv;
        return new ModelAndView("redirect:/Login");
    }

}
