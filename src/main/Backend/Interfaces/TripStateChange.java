package main.Backend.Interfaces;

import main.Database.Entities.Trip;

public interface TripStateChange {
    void OnTripStateChange(Trip t);
}
