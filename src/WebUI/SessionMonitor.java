package WebUI;

public interface SessionMonitor {
    void SessionExpired();
    void SessionExtended();
    void SessionTerminated();
}
