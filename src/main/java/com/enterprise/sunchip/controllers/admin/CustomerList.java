package main.java.com.enterprise.sunchip.controllers.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CustomerList {

    @RequestMapping(value = "customers", method = RequestMethod.GET)
        public ModelAndView toCustomerList()
        {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("pages/customers/customers");
            System.out.println(mv);
            return mv;
        }


}
