package ride.sharing;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Platform {
    public static final int DEFAULT_FARE = 20;
    private final Map<String, Driver> drivers = new HashMap<>();
    private final Map<String, Rider> riders = new HashMap<>();
    private final Map<String, Ride> rides = new HashMap<>();
    private final Random random;

    Platform() {
        random = new Random();
    }

    public static void main(String[] args) {
        Platform p = new Platform();
        p.addDriver("Ramesh");
        p.addDriver("Suresh");
        p.addRider("A");
        p.addRider("B");
        p.addRider("C");
        Rider a = p.riders.get("A");
        a.createRide(10, 20, 1);
        a.closeRide();
        Rider b = p.riders.get("B");
        b.createRide(10, 60, 3);
        b.closeRide();
        a.createRide(30, 80, 2);
        a.withdrawRide();
        for (int i = 0; i < 10; i++) {
            Rider c = p.riders.get("C");
            c.createRide(i * 10, 2 * i * 10, i + 1);
            c.closeRide();
        }
        System.out.println("drivers = " + p.drivers);
        System.out.println("riders = " + p.riders);
        System.out.println("rides = " + p.rides);
        System.out.println("c = " + p.riders.get("C").toDetailedString());
    }

    public Ride getRideInfo(String id) {
        return rides.get(id);
    }

    public void addDriver(String name) {
        drivers.put(name, new Driver(name, this));
    }

    public void addRider(String name) {
        riders.put(name, new Rider(name, this));
    }

    public Ride bookRide(int origin, int destination, int noOfSeats) {
        Ride r = new Ride(this, origin, destination, noOfSeats);
        Driver[] poolOfDrivers = drivers.values().toArray(new Driver[0]);
        Driver pickedDriver = poolOfDrivers[random.nextInt(poolOfDrivers.length)];
        pickedDriver.addRide(r.getId());
        r.setDriver(pickedDriver);
        rides.put(r.getId(), r);
        return r;
    }


    public enum Status {AVAILABLE, BOOKED, CLOSED, WITHDRAWN}
}
