package main.Backend;

import main.Backend.Interfaces.TripStateChange;
import main.Common.Shared;
import main.Database.Entities.Trip;
import main.Database.Entities.TripRejectionReason;
import main.Database.Entities.User;
import jdk.jshell.spi.ExecutionControl;

import java.sql.SQLException;
import java.util.*;

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
    public Trip RequestNewTrip(User customer) throws SQLException {
        if (this.GetActiveTripsByCustomer(customer).isEmpty()) {
            var trip = new Trip();
            trip.TripComplete = false;
            trip.Customer = customer;

            Shared.DbContext.Trips.create(trip);


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


    public ArrayList<Trip> GetNotCompleteTripsByCustomer(User customer) {
        var matching_trips = new ArrayList<Trip>();
        for (Trip ct : Shared.DbContext.Trips) {
            if ((ct.Customer.ID.equals(customer.ID)) && (!ct.TripComplete)) matching_trips.add(ct);
        }
        return matching_trips;
    }


    /**
     * Assigns first availble driver to a trip. Drivers are required to have no active trips
     * @param t Trip to be assigned
     * @return Boolean : Indicates if the trip got assigned
     */
    public boolean AssignTripToDriver(Trip t) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Go away");
//        var Drivers = Shared.BeContext.User.GetAllDrivers();
//        for (var d : Drivers) {
//            if (this.GetActiveTripsByDriver(d).isEmpty()) {
//                t.Driver = d;
//                t.State = Trip.TripState.IN_PROGRESS;
//                return true;
//            }
//        }
//        return false;
    }

    /**
     * Attempt to assign a specific driver to a trip. The driver is required to have no active trips
     * @param t Trip to be assign
     * @param d Driver to assign
     * @return Boolean : Indicates if the trip go assigned
     */
    public boolean AssignTripDriver(Trip t, User d) throws SQLException {
        if (this.GetActiveTripsByDriver(d).isEmpty()) {
            t.Driver = d;
            t.State = Trip.TripState.IN_PROGRESS;
            Shared.DbContext.Trips.update(t);
            return true;
        }
        return false;
    }

    public boolean AssignTripDriverAwaitingPickup(Trip t, User d) throws SQLException {
        if (this.GetActiveTripsByDriver(d).isEmpty()) {
            t.Driver = d;
            t.State = Trip.TripState.AWAITING_PICKUP;
            Shared.DbContext.Trips.update(t);
            return true;
        }
        return false;
    }



    /**
     * Some junk to do some internal processing because Javas
     * native support to .NET Core and .NET Framework is absolute
     * shit
     */
    public class TripCount implements Comparable<TripCount> {
        public User Driver;
        public int Count;

        public TripCount(User driver, Integer count) {
            this.Driver = driver;
            this.Count = count;
        }

        @Override
        public int compareTo(TripModule.TripCount o) {
            if (o.Count > this.Count) return -1;
            if (o.Count < this.Count) return 1;
            return 0;
        }
    }

    /**
     * Returns a list of object. It will help you get a list of sorted
     * list of drivers by how many drivers they have
     * @return List of drivers
     */
    public List<TripCount> GetUserTripCount() {
        var driverList = Shared.BeContext.User.GetAllDrivers();
        var returnObj = new ArrayList<TripCount>();
        for (var d : driverList) {
            returnObj.add(new TripCount(d, GetAllTripsByDriver(d).size()));
        }
        Collections.sort(returnObj);
        return returnObj;
    }

    /**
     * This function will accept a trip and try to assign a driver
     * to the trip. It will prioritize assigning a trip to the drivers
     * with the least amount of trips in their pocket
     * @param t Trip
     * @return Driver that was assigned. Null if wasn't assigned
     */
    public User AssignTripToAvailableDriver(Trip t) throws SQLException {
        var availableDrivers = GetUserTripCount();
        for (var d : availableDrivers) {
            if (GetActiveTripsByDriver(d.Driver).isEmpty()) {
                AssignTripDriver(t, d.Driver);
                return d.Driver;
            }
        }
        return null;
    }

    public User AssignTripToAvailableDriverForPickup(Trip t) throws SQLException {
        var availableDrivers = GetUserTripCount();
        for (var d : availableDrivers) {
            if (GetActiveTripsByDriver(d.Driver).isEmpty()) {
                AssignTripDriverAwaitingPickup(t, d.Driver);
                return d.Driver;
            }
        }
        return null;
    }

    protected List<Trip> TripPool = new ArrayList<Trip>();
    protected List<main.Backend.Interfaces.TripStateChange> TripStateChangeListeners = new ArrayList<TripStateChange>();
    protected TripPoolHandler poolHandler;

    /**
     * Get the trip pool list
     * @return trip pool list
     */
    public List<Trip> GetTripPool() {
        return TripPool;
    }

    /**
     * Use this function to subscribe to the event when a trip state has been changed
     * @param l Class with the TripStateChange interface implemented
     */
    public void SubscribeToTripStateChange(TripStateChange l) {
        TripStateChangeListeners.add(l);
    }

    /**
     * Use this function to unsubscribe to the event when a trip state has been changed
     * @param l Class with the TripStateChange interface implemented
     */
    public void UnsubscribeFromTripStateChange(TripStateChange l) {
        TripStateChangeListeners.remove(l);
    }

    /**
     * Use this function to add a trip to the pool queue. The the backend will then automatically
     * try to assign a trip to an available driver
     * @param t Trip to add
     */
    public void AddTripToPool(Trip t) {
        TripPool.add(t);
    }

    /**
     * Use this function to remove a trip from the pool queue
     * @param t Trip to remove
     */
    public void RemoveTripFromPool(Trip t) {
        TripPool.remove(t);
    }


    /**
     * This function will start the thread to process new pools
     */
    public void StartTripPoolHandler() {
        poolHandler = new TripPoolHandler(this);
        poolHandler.start();
    }

    /**
     * This function will notify the listeners that a trip state has been changed
     * @param p Trip
     */
    protected void NotifyTripStateChange(Trip p) {
        for (var l : TripStateChangeListeners) {
            l.OnTripStateChange(p);
        }
    }

    /**
     * This function will mark the trip as complete
     * @param p Trip to mark as complete
     * @throws SQLException Standard SQL Exception
     */
    public void MarkTripAsComplete(Trip p) throws SQLException {
        p.State = Trip.TripState.COMPLETE;
        p.TripComplete = true;
        Shared.DbContext.Trips.update(p);
    }

    /**
     * This function will reject a trip
     * @param t Trip to reject
     * @param d Driver
     * @param reason Reason for rejection
     * @throws SQLException Standard SQL Exception
     */
    public void RejectByDriver(Trip t, User d, String reason) throws SQLException {
        TripRejectionReason r = new TripRejectionReason();
        r.Driver = d;
        r.RejectionReason = reason;
        t.RejectionReasons.add(r);
        t.State = Trip.TripState.REJECTED;
        Shared.DbContext.Trips.update(t);
    }

    /**
     * This function will accept 2 dates and it will
     * return the trips that were created between those
     * two dates
     * @param startDate Start date
     * @param endDate End Date
     * @return Trips within range
     */
    public List<Trip> GetTripWithinDateRange(Date startDate, Date endDate) throws Exception {
        if (endDate.before(startDate)) throw new Exception("Start date must be after the end date");
        var returnList = new ArrayList<Trip>();
        for (var t : Shared.DbContext.Trips) {
            if (t.CreationTime.after(startDate) && t.CreationTime.before(endDate)) {
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * This function will accept a list of trips and filter them by
     * driver
     * @param l List of trips
     * @param driver Driver
     * @return Filtered trip list
     */
    public List<Trip> FilterByDriver(List<Trip> l, User driver) {
        var return_list = new ArrayList<Trip>();
        for (var t : l) {
            if (t.Driver.getID() == driver.getID()) {
                return_list.add(t);
            }
        }
        return return_list;
    }


    public List<Trip> GetTripWithinDateRangeAndDriver(Date startDate, Date endDate, Integer driverId) throws Exception {
        if (endDate.before(startDate)) throw new Exception("Start date must be after the end date");
        var returnList = new ArrayList<Trip>();
        for (var t : Shared.DbContext.Trips) {
            if (t.CreationTime.after(startDate) && t.CreationTime.before(endDate) && t.Driver.ID.equals(driverId)) {
                returnList.add(t);
            }
        }
        return returnList;
    }

}
