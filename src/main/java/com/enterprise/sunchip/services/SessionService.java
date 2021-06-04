package com.enterprise.sunchip.services;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class SessionService {

    public SessionService() {
    }

    public void storeTokenInLocalCashe(HttpServletRequest request, String token) {
        HttpSession session = request.getSession();
        session.setAttribute("usertoken", token);
    }

    public String getTokenStoredInLocalCahse(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute("usertoken");
    }

}
