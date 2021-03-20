package com.enterprise.sunchip.controllers;

import com.enterprise.sunchip.dao.UserAccountRepo;
import com.enterprise.sunchip.models.UserAccount;
import com.enterprise.sunchip.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class UserController {

    @Autowired
    UserAccountRepo repo;

    @Autowired
    UserAccountService service;

    @RequestMapping("/CreateUser")
    public ModelAndView goToCreateView()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("User/CreateUserView");
        return mv;
    }

    /**
     * Even if user account or password is missing, it will still insert the record into the db.
     * Do error handling later
     *
     * also, usernames can have duplicates. prolly should let that be distinct..
     *
     * @param useraccount
     * @param bindingResult
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    @RequestMapping("/CreateAdminNewUser")
    public ModelAndView createNewUser(@ModelAttribute UserAccount useraccount, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        useraccount.setType("ADMIN");
        repo.save(useraccount);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("User/MainUserView");
        return mv;
    }

    @RequestMapping("/ViewUsers")
    public ModelAndView viewUserAccounts(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("accounts", service.findAllUserAccounts());
        mv.setViewName("User/ListUserView");
        return mv;
    }

    /**
     * Prolly gonna need a publically available user object which also holds the user ID
     *
     * @param req
     * @param res
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/LogUserAccountIn")
    public ModelAndView login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView();

        try {
            String name = req.getParameter("username");
            String password = req.getParameter("password");
            ArrayList<UserAccount> userAccountArrayList = service.findAllUserAccounts();

            for (UserAccount user : userAccountArrayList) {
                if (user.getUsername().contentEquals(name)) {
                    if (user.getPassword().contentEquals(password)) {
                        mv.setViewName("User/MainUserView");
                        return mv;
                    }
                }
            }

        } catch (Exception e) {
            // password not given
            // user name not given
            // not registered user
        }
        mv.setViewName("index");
        return mv;
    }

}
