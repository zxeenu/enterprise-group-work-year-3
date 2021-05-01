package Backend;

import Common.Shared;
import Database.Entities.Trip;
import Database.Entities.User;

import java.util.ArrayList;

public class TripModule {


    public TripModule() { }

    /**
     * Gets all the trips in the system
     * @return ArrayList of all the trips
     */
    public ArrayList<Trip> GetAllTrips() {
        var rl = new ArrayList<Trip>();
        for (Trip trip : Shared.DbContext.Trips) {
            rl.add(trip);
        }
        return rl;
    }

    /**
     * Create a new trip. Requires a customer. The customer will be required to have no active trips in their record.
     * @param customer Customer to use
     * @return Created trip object
     */
    public Trip RequestNewTrip(User customer) {
        if (this.GetActiveTripsByCustomer(customer).isEmpty()) {
            var trip = new Trip();
            trip.TripComplete = false;
            trip.Customer = customer;
            return trip;
        }
        return null;
    }

    /**
     * Gets all the trips driven by a specific driver. Includes Active Trips
     * @param driver Driver to match
     * @return ArrayList of matching trips
     */
    public ArrayList<Trip> GetAllTripsByDriver(User driver) {
        var matching_trips = new ArrayList<Trip>();
        for (Trip ct : Shared.DbContext.Trips) {
            if (ct.Driver != null) {
                if (ct.Driver.ID.equals(driver.ID)) matching_trips.add(ct);
            }
        }
        return matching_trips;
    }

    /**
     * Gets all the active trips by specific driver
     * @param driver Driver to match
     * @return ArrayList of active trips
     */
    public ArrayList<Trip> GetActiveTripsByDriver(User driver) {
        var matching_trips = new ArrayList<Trip>();
        for (Trip ct : Shared.DbContext.Trips) {
            if (ct.Driver != null) {
                if ((ct.Driver.ID.equals(driver.ID)) && (!ct.TripComplete)) matching_trips.add(ct);
            }
        }
        return matching_trips;
    }

    /**
     * Gets all the trips registered by a specific customer. Includes active trips
     * @param customer Customer to match
     * @return ArrayList of Customer
     */
    public ArrayList<Trip> GetAllTripsByCustomer(User customer) {
        var matching_trips = new ArrayList<Trip>();
        for (Trip ct : Shared.DbContext.Trips) {
            if (ct.Driver != null) {
                if (ct.Customer.ID.equals(customer.ID)) matching_trips.add(ct);
            }
        }
        return matching_trips;
    }

    /**
     * Gets the active trip by a specific customer
     * @param customer Customer to match
     * @return ArrayList of Customer
     */
    public ArrayList<Trip> GetActiveTripsByCustomer(User customer) {
        var matching_trips = new ArrayList<Trip>();
        for (Trip ct : Shared.DbContext.Trips) {
            if (ct.Driver != null) {
                if ((ct.Customer.ID.equals(customer.ID)) && (!ct.TripComplete)) matching_trips.add(ct);
            }
        }
        return matching_trips;
    }


    /**
     * Assigns first availble driver to a trip. Drivers are required to have no active trips
     * @param t Trip to be assigned
     * @return Boolean : Indicates if the trip got assigned
     */
    public boolean AssginTripToDriver(Trip t) {
        var Drivers = Shared.BeContext.User.GetAllDrivers();
        for (var d : Drivers) {
            if (this.GetActiveTripsByDriver(d).isEmpty()) {
                t.Driver = d;
                return true;
            }
        }
        return false;
    }

    /**
     * Attempt to assign a specific driver to a trip. The driver is required to have no active trips
     * @param t Trip to be assign
     * @param d Driver to assign
     * @return Boolean : Indicates if the trip go assigned
     */
    public boolean AssignTripDriver(Trip t, User d) {
        if (this.GetActiveTripsByDriver(d).isEmpty()) {
            t.Driver = d;
            return true;
        }
        return false;
    }


}
