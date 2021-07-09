package Tests;

import main.Backend.BackendContext;
import main.Database.Entities.Trip;
import main.Database.Entities.User;
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

    /**
     * This will generate dummy users
     * @throws SQLException
     */
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

    /**
     * This function will make sure that the auto assignment works properly
     * @throws SQLException
     * @throws InterruptedException
     */
    @Test
    public void AutoAssignmentTest() throws SQLException, InterruptedException {
        BeContext.DropAllTables();
        AddDummyUsers();
        var CustomerATrip = BeContext.Trip.RequestNewTrip(CustomerA);
        var CustomerBTrip = BeContext.Trip.RequestNewTrip(CustomerB);

        BeContext.Trip.AddTripToPool(CustomerATrip);
        BeContext.Trip.AddTripToPool(CustomerBTrip);

        Thread.sleep(1000);
        Assert.assertEquals(Trip.TripState.IN_PROGRESS, CustomerBTrip.State);
        Assert.assertEquals(Trip.TripState.IN_PROGRESS, CustomerATrip.State);
    }

    /**
     * This function makes sure that auto assignment and queueing works
     * @throws SQLException
     * @throws InterruptedException
     */
    @Test
    public void AutoAssignmentQueueTest() throws SQLException, InterruptedException {
        AddDummyUsers();
        var CustomerATrip = BeContext.Trip.RequestNewTrip(CustomerA);
        var CustomerBTrip = BeContext.Trip.RequestNewTrip(CustomerB);
        var CustomerCTrip = BeContext.Trip.RequestNewTrip(CustomerC);

        BeContext.Trip.AddTripToPool(CustomerATrip);
        BeContext.Trip.AddTripToPool(CustomerBTrip);
        BeContext.Trip.AddTripToPool(CustomerCTrip);

        if (CustomerCTrip.State == Trip.TripState.IN_PROGRESS) Assert.fail("Customer C trip processed unexpectedly");

        Thread.sleep(1000);

        BeContext.Trip.MarkTripAsComplete(CustomerATrip);
        BeContext.Trip.MarkTripAsComplete(CustomerBTrip);

        Thread.sleep(1000);

        if (CustomerATrip.State != Trip.TripState.COMPLETE) Assert.fail("Customer A Trip was not completed");
        if (CustomerBTrip.State != Trip.TripState.COMPLETE) Assert.fail("Customer B Trip was not completed");
        if (CustomerCTrip.State != Trip.TripState.IN_PROGRESS) Assert.fail("Customer C Trip was not processed");
    }

    /**
     * This function will make sure that trip state change events work properly
     * @throws SQLException
     * @throws InterruptedException
     */
    @Test
    public void ListenerSubscriptionTest() throws SQLException, InterruptedException {
        AddDummyUsers();
        var CustomerATrip = BeContext.Trip.RequestNewTrip(CustomerA);
        var listener = new DummyListener();
        BeContext.Trip.SubscribeToTripStateChange(listener);
        BeContext.DbContext.Trips.create(CustomerATrip);
        BeContext.Trip.AddTripToPool(CustomerATrip);
        Thread.sleep(1000);
        if (!listener.TripCompleted || listener.trip != CustomerATrip) {
            Assert.fail();
        }
    }

    /**
     * This function will make sure that the rejection reason sticks in the database
     * @throws SQLException
     */
    @Test
    public void RejectionReasonsPersistenceTest() throws SQLException {
        AddDummyUsers();
        var CustomerATrip = BeContext.Trip.RequestNewTrip(CustomerA);
        var CustomerBTrip = BeContext.Trip.RequestNewTrip(CustomerB);

        BeContext.Trip.RejectByDriver(CustomerATrip, DriverA, "Driver A Rejection Reason For Trip A");
        BeContext.Trip.RejectByDriver(CustomerATrip, DriverB, "Driver B Rejection Reason For Trip A");
        BeContext.Trip.RejectByDriver(CustomerBTrip, DriverB, "Driver A Rejection Reason For Trip B");
    }

    /**
     * This function makes sure that rejection properly works
     * @throws SQLException
     * @throws InterruptedException
     */
    @Test
    public void RejectedHistoryTest() throws SQLException, InterruptedException {
        AddDummyUsers();

        var CustomerATrip = BeContext.Trip.RequestNewTrip(CustomerA);

        BeContext.Trip.AddTripToPool(CustomerATrip);

        BeContext.Trip.RejectByDriver(CustomerATrip, DriverA, "Driver A Rejected");
        BeContext.Trip.RejectByDriver(CustomerATrip, DriverB, "Driver B Rejected");

        Thread.sleep(3000);

        if (CustomerATrip.State != Trip.TripState.AWAITING_PICKUP) Assert.fail("Rejection history was ignored!");
    }

    public class DummyListener implements main.Backend.Interfaces.TripStateChange {
        public boolean TripCompleted = false;
        public Trip trip = null;


        @Override
        public void OnTripStateChange(Trip t) {
            TripCompleted = true;
            trip = t;
        }
    }




}
