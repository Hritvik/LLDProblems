package ride.sharing;

import java.util.ArrayList;
import java.util.List;

public class Rider {
    private final Platform p;
    private final String name;
    private final List<String> rides;
    private boolean preferred = false;
    private String currentRide;

    Rider(String name, Platform p) {
        this.name = name;
        this.p = p;
        rides = new ArrayList<>();
    }

    public void createRide(int orig, int dest, int noOfSeats) {
        Ride r = p.bookRide(orig, dest, noOfSeats);
        r.setRider(this);
        currentRide = r.getId();
        rides.add(r.getId());
    }

    public void updateRide(String id, int orig, int dest, int noOfSeats) {
        Ride r = p.getRideInfo(id);
        r.setOrig(orig);
        r.setDest(dest);
        r.setNoOfSeats(noOfSeats);
    }

    public int closeRide() {
        p.getRideInfo(currentRide).setStatus(Platform.Status.CLOSED);
        if (!preferred) {
            int successfulRides = (int) rides.stream().filter(r -> p.getRideInfo(r).getStatus().equals(Platform.Status.CLOSED)).count();
            if (successfulRides >= 10) {
                preferred = true;
            }
        }
        int fare = p.getRideInfo(currentRide).calculateFare();
        currentRide = null;
        return fare;
    }

    public void withdrawRide() {
        p.getRideInfo(currentRide).setStatus(Platform.Status.WITHDRAWN);
        currentRide = null;
    }

    @Override
    public String toString() {
        return "Rider{" +
                "name='" + name + '\'' +
                '}';
    }

    public String toDetailedString() {
        return "Rider{" +
                "name='" + name + '\'' +
                ", rides=" + rides +
                ", preferred=" + preferred +
                ", currentRide='" + currentRide + '\'' +
                '}';
    }
}
