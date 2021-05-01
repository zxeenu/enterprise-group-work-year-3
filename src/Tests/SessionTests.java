import WebUI.Session;
import WebUI.SessionMonitor;
import org.junit.Assert;
import org.junit.Test;

public class SessionTests {

    Session ts;
    TestSubscriber tsm;

    public SessionTests() {
        this.ts = new Session();
        this.tsm = new TestSubscriber();
    }

    @Test
    public void SessionTerminatedTest() {
        ts.Subscribe(tsm);
        ts.TerminateSession();
        Assert.assertTrue(tsm.HasSessionTerminated);
        Assert.assertFalse(tsm.HasSessionExpired);
    }

    @Test
    public void SessionExpiredTest() throws InterruptedException {
        ts.Subscribe(tsm);
        ts.ExpirationPeriod = 1000;
        ts.StartSession();
        Thread.sleep(1005);
        Assert.assertTrue(tsm.HasSessionExpired);
        Assert.assertTrue(tsm.HasSessionTerminated);
    }

    @Test
    public void SessionExtendTest() throws InterruptedException {
        ts.Subscribe(tsm);
        ts.ExpirationPeriod = 3000;
        Thread.sleep(2000);
        ts.ExtendSession();
        Thread.sleep(2000);
        Assert.assertFalse(tsm.HasSessionExpired);
        Assert.assertTrue(tsm.HasSessionExtended);
        Assert.assertFalse(tsm.HasSessionTerminated);
    }

    public class TestSubscriber implements SessionMonitor {

        public boolean HasSessionExpired;
        public boolean HasSessionExtended;
        public boolean HasSessionTerminated;

        public TestSubscriber() {
            this.HasSessionExpired = false;
            this.HasSessionExtended = false;
            this.HasSessionTerminated = false;
        }

        @Override
        public void SessionExpired() {
            this.HasSessionExpired = true;
            System.out.println("Session expired");
        }

        @Override
        public void SessionExtended() {
            this.HasSessionExtended = true;
            System.out.println("Session extended");
        }

        @Override
        public void SessionTerminated() {
            this.HasSessionTerminated = true;
            System.out.println("Session terminated");
        }
    }
}
