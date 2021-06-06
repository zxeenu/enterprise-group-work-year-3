package WebUI;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Session {

    private ArrayList<SessionMonitor> Subscribers;
    public Database.Entities.User User;
    public long ExpirationPeriod;
    private final TimerTask KillSessionTask;
    private final Timer ExpirationTimer = new Timer();


    public Session() {
        this.ExpirationPeriod = (10 * 60 * 1000);
        this.Subscribers = new ArrayList<>();
        this.KillSessionTask = new TimerTask() {
            @Override
            public void run() {
                CallExpiredEvent();
                TerminateSession();
            }
        };
    }

    private void CallExpiredEvent() {
        for (var x : this.Subscribers) {
            x.SessionExpired(this);
        }
    }

    public void StartSession() {
        this.ExpirationTimer.schedule(KillSessionTask, ExpirationPeriod);
    }

    public void TerminateSession() {
        this.ExpirationTimer.cancel();
        for (var x : this.Subscribers) {
            x.SessionTerminated(this);
        }
    }

    public void ExtendSession() {
        this.ExpirationTimer.schedule(this.KillSessionTask, ExpirationPeriod);
        for (var x : this.Subscribers) {
            x.SessionExtended(this);
        }
    }

    public void Subscribe(SessionMonitor e) {
        this.Subscribers.add(e);
    }

    public void Unsubscribe(SessionMonitor e) {
        this.Subscribers.remove(e);
    }
}


