package main.java.com.enterprise.sunchip.controllers.admin;

import Common.Shared;
import main.java.com.enterprise.sunchip.services.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AdminDriverCreatorController {

    @Autowired
    private LocalSession localSession;

    @RequestMapping(value = "CreateDriver", method = RequestMethod.GET)
    public ModelAndView createDriver(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/error/Error");
        mv.addObject("errorMessage", "sorry, you need to login before you can use this page!");
        String tokenId = "";

        try {
            tokenId = localSession.getTokenStoredInLocalCashe(request);

            if (!tokenId.isEmpty()) {
                var admin = Shared.BeContext.User.GetByPasswordHash(tokenId);

                if (admin != null) {
                    if (admin.UserClassCode == 1) {
                        mv.setViewName("pages/admin/AddDriver");
                        mv.addObject("admin", admin);
                    }
                }
            }

        } catch (Exception e) {
            localSession.clearTokenStoredInLocalCashe(request);
        }
        return mv;
    }

}
