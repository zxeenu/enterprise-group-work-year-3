package Backend.Interfaces;

import Database.Entities.Trip;

public interface TripStateChange {
    void OnTripStateChange(Trip t);
}
