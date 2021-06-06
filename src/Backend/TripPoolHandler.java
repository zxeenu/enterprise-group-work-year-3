package Backend;

import Common.Logger;
import Database.Entities.Trip;
import org.junit.platform.commons.util.ExceptionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripPoolHandler extends Thread {

    TripModule tripModule;

    public TripPoolHandler(TripModule m) {
        this.tripModule = m;
    }

    public void run() {
        while (true) {

            List<Trip> processed = new ArrayList<>();
            try {
                for (var p : tripModule.TripPool) {
                    if (p.State == Trip.TripState.IN_PROGRESS) continue;;
                    var driver = tripModule.AssignTripToAvailableDriver(p);
                    if (driver != null) {
                        processed.add(p);
                    }
                }
                for (var t : processed) {
                    t.State = Trip.TripState.IN_PROGRESS;
                    tripModule.TripPool.remove(t);
                    tripModule.NotifyTripStateChange(t);
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
