package Tests;

import Backend.BackendContext;
import Database.Entities.Trip;
import Database.Entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class TripHandlerTests {
    BackendContext BeContext;

    public TripHandlerTests() throws SQLException {
        BeContext = new BackendContext(Const.JUnitConnectionString);
        BeContext.DropAllTables();
        BeContext.DbContext.CheckAndRebuildDb();
    }

    User DriverA;
    User DriverB;

    User CustomerA;
    User CustomerB;
    User CustomerC;

    public void AddDummyUsers() throws SQLException {
        DriverA = new User() {{
           FirstName = "DriverA";
            UserClassCode = 2;
        }};

        DriverB = new User() {{
            FirstName = "DriverB";
            UserClassCode = 2;
        }};

        CustomerA = new User() {{
            FirstName = "CustomerA";
            UserClassCode = 3;
        }};

        CustomerB = new User() {{
            FirstName = "CustomerB";
            UserClassCode = 3;
        }};

        CustomerC = new User() {{
            FirstName = "CustomerC";
            UserClassCode = 3;
        }};

        BeContext.DbContext.Users.create(DriverA);
        BeContext.DbContext.Users.create(DriverB);
        BeContext.DbContext.Users.create(CustomerA);
        BeContext.DbContext.Users.create(CustomerB);
        BeContext.DbContext.Users.create(CustomerC);
    }

    @Test
    public void AutoAssignmentTest() throws SQLException, InterruptedException {
        AddDummyUsers();
        var CustomerATrip = BeContext.Trip.RequestNewTrip(CustomerA);
        var CustomerBTrip = BeContext.Trip.RequestNewTrip(CustomerB);

        BeContext.Trip.AddTripToQueue(CustomerATrip);
        BeContext.Trip.AddTripToQueue(CustomerBTrip);
        Thread.sleep(1500);
        Assert.assertTrue((
                CustomerATrip.State == Trip.TripState.IN_PROGRESS && CustomerBTrip.State == Trip.TripState.IN_PROGRESS
                ));
    }

    @Test
    public void AutoAssignmentQueueTest() throws SQLException, InterruptedException {
        AddDummyUsers();
        var CustomerATrip = BeContext.Trip.RequestNewTrip(CustomerA);
        var CustomerBTrip = BeContext.Trip.RequestNewTrip(CustomerB);
        var CustomerCTrip = BeContext.Trip.RequestNewTrip(CustomerC);

        BeContext.Trip.AddTripToQueue(CustomerATrip);
        BeContext.Trip.AddTripToQueue(CustomerBTrip);
        BeContext.Trip.AddTripToQueue(CustomerCTrip);

        if (CustomerCTrip.State == Trip.TripState.IN_PROGRESS) Assert.fail("Customer C trip processed unexpectedly");

        Thread.sleep(1000);

        BeContext.Trip.MarkTripAsComplete(CustomerATrip);
        BeContext.Trip.MarkTripAsComplete(CustomerBTrip);

        Thread.sleep(1000);

        if (CustomerATrip.State != Trip.TripState.COMPLETE) Assert.fail("Customer A Trip was not completed");
        if (CustomerBTrip.State != Trip.TripState.COMPLETE) Assert.fail("Customer B Trip was not completed");
        if (CustomerCTrip.State != Trip.TripState.IN_PROGRESS) Assert.fail("Customer C Trip was not processed");
    }

    @Test
    public void ListenerSubscriptionTest() throws SQLException, InterruptedException {
        AddDummyUsers();
        var CustomerATrip = BeContext.Trip.RequestNewTrip(CustomerA);
        var listener = new DummyListener();
        BeContext.Trip.SubscribeToTripStateChange(listener);
        BeContext.DbContext.Trips.create(CustomerATrip);
        BeContext.Trip.AddTripToQueue(CustomerATrip);
        Thread.sleep(1000);
        if (!listener.TripCompleted || listener.trip != CustomerATrip) {
            Assert.fail();
        }
    }

    public class DummyListener implements Backend.Interfaces.TripStateChange {
        public boolean TripCompleted = false;
        public Trip trip = null;
        @Override
        public void OnTripStateChange(Trip t) {
            TripCompleted = true;
            trip = t;
        }
    }


}
