package com.enterprise.sunchip.models;

import WebUI.Session;
import WebUI.SessionMonitor;

public class WebUIManager implements SessionMonitor {

    public boolean HasSessionExpired;
    public boolean HasSessionExtended;
    public boolean HasSessionTerminated;
    public String tokenId;

    public WebUIManager() {
        this.HasSessionExpired = false;
        this.HasSessionExtended = false;
        this.HasSessionTerminated = false;
        this.tokenId = "";
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    @Override
    public void SessionExpired(Session s) {
        this.HasSessionExpired = true;
        System.out.println("Session expired");
    }

    @Override
    public void SessionExtended(Session s) {
        this.HasSessionExtended = true;
        System.out.println("Session extended");
    }

    @Override
    public void SessionTerminated(Session s) {
        this.HasSessionTerminated = true;
        System.out.println("Session terminated");
    }
}
