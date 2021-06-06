package Backend;

import Common.Logger;
import Database.Entities.Trip;
import org.junit.platform.commons.util.ExceptionUtils;

import java.util.Arrays;

public class TripPoolHandler extends Thread {

    TripModule tripModule;

    public TripPoolHandler(TripModule m) {
        this.tripModule = m;
    }

    public void run() {
        while (true) {
            try {
                for (var p : tripModule.TripPool) {
                    if (p.State == Trip.TripState.IN_PROGRESS) continue;;
                    var driver = tripModule.AssignTripToAvailableDriver(p);
                    if (driver != null) {
                        p.State = Trip.TripState.IN_PROGRESS;
                        tripModule.TripPool.remove(p);
                        tripModule.NotifyTripStateChange(p);
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                Logger.Instance.Add("TripPoolHandler Exception!", Logger.LogLevels.CRITICAL);
                Logger.Instance.Add(e.getMessage(), Logger.LogLevels.CRITICAL);
                Logger.Instance.Add(ExceptionUtils.readStackTrace(e), Logger.LogLevels.CRITICAL);
            }
        }
    }
}
