package main.java.com.enterprise.sunchip.controllers.signup;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SignUpController {

    @RequestMapping(value = "Login", method = RequestMethod.GET)
    public ModelAndView toLoginPage()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login/NormalLogin");
        System.out.println(mv);
        return mv;
    }

//    @RequestMapping(value = "SignUp", method = RequestMethod.POST)
//    public ModelAndView SignUpAction()
//    {
//        //Create user
//    }

}
