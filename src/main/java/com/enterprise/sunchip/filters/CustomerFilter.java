package main.java.com.enterprise.sunchip.filters;

import Common.Shared;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerFilter implements HandlerInterceptor {

    private final Logger Log = LoggerFactory.getLogger(CustomerFilter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Log.info("Prehandle customer fliter interception");


        HttpSession session = request.getSession();
        String tokenId = (String) session.getAttribute("usertoken");

        if (tokenId.isEmpty()) {
            var user = Shared.BeContext.User.GetByPasswordHash(tokenId);

            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/Logout");
            }
        }
        return true;

//        boolean chain = true;
//        try {
//            var user = Shared.BeContext.User.GetByPasswordHash(tokenId);
//
//            if (user.UserClassCode != 3) {
//                session.setAttribute("usertoken", "");
//                response.sendRedirect(request.getContextPath() + "/Logout");
//            } else if (user.UserClassCode == 3) {
//                chain = false;
//            }
//
//        } catch (Exception ignored) {
//            session.setAttribute("usertoken", "");
//            response.sendRedirect(request.getContextPath() + "/Logout");
//        }
//        return chain;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Log.info("Posthandle customer fliter interception");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Log.info("Completion customer fliter interception");
    }
}