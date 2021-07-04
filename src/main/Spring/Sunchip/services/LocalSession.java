package main.Spring.Sunchip.services;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class LocalSession {

    public void storeTokenInLocalCashe(HttpServletRequest request, String token) {
        HttpSession session = request.getSession();
        session.setAttribute("usertoken", token);
    }

    public String getTokenStoredInLocalCashe(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute("usertoken");
    }

    public void clearTokenStoredInLocalCashe(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("usertoken", "");
    }

}
