package WebUI;

public interface SessionMonitor {
    void SessionExpired(Session s);
    void SessionExtended(Session s);
    void SessionTerminated(Session s);
}
