package Tests;

import main.WebUI.Session;
import main.WebUI.SessionMonitor;
import org.junit.Assert;
import org.junit.Test;

public class SessionTests {

    Session UserSession;
    WebUIManager WebsiteUIManager;

    // Constructor
    public SessionTests() {
        this.UserSession = new Session();
        this.WebsiteUIManager = new WebUIManager();
    }

    /**
     * This function will make sure that the web session unsubscribe
     * function works
     */
    @Test
    public void UnsubscribeTest() {
        UserSession.Subscribe(WebsiteUIManager);
        UserSession.Unsubscribe(WebsiteUIManager);
        UserSession.TerminateSession();
        Assert.assertFalse(WebsiteUIManager.HasSessionTerminated);
    }

    /**
     * This function will make sure that the session terminated
     * event gets observed
     */
    @Test
    public void SessionTerminatedTest() {
        UserSession.Subscribe(WebsiteUIManager);
        UserSession.TerminateSession();
        Assert.assertTrue(WebsiteUIManager.HasSessionTerminated);
        Assert.assertFalse(WebsiteUIManager.HasSessionExpired);
    }

    /**
     * This function will make sure that the session expired after
     * a specified time period
     * @throws InterruptedException
     */
    @Test
    public void SessionExpiredTest() throws InterruptedException {
        UserSession.Subscribe(WebsiteUIManager);
        UserSession.ExpirationPeriod = 1000;
        UserSession.StartSession();
        Thread.sleep(1005);
        Assert.assertTrue(WebsiteUIManager.HasSessionExpired);
        Assert.assertTrue(WebsiteUIManager.HasSessionTerminated);
    }

    /**
     * This function will make sure that the session extension works
     * properly
     * @throws InterruptedException
     */
    @Test
    public void SessionExtendTest() throws InterruptedException {
        UserSession.Subscribe(WebsiteUIManager);
        UserSession.ExpirationPeriod = 3000;
        Thread.sleep(2000);
        UserSession.ExtendSession();
        Thread.sleep(2000);
        Assert.assertFalse(WebsiteUIManager.HasSessionExpired);
        Assert.assertTrue(WebsiteUIManager.HasSessionExtended);
        Assert.assertFalse(WebsiteUIManager.HasSessionTerminated);
    }

    public class WebUIManager implements SessionMonitor {

        public boolean HasSessionExpired;
        public boolean HasSessionExtended;
        public boolean HasSessionTerminated;

        public WebUIManager() {
            this.HasSessionExpired = false;
            this.HasSessionExtended = false;
            this.HasSessionTerminated = false;
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
}
