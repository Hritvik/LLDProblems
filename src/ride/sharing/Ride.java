package ride.sharing;

import ride.sharing.Platform.Status;

import java.util.UUID;

public class Ride {
    private final String id;
    private final Platform p;
    private int orig;
    private int dest;
    private int noOfSeats;
    private Status status = Status.AVAILABLE;
    private Driver driver;
    private Rider rider;

    Ride(Platform p, int origin, int destination, int noOfSeats) {
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.p = p;
        this.orig = origin;
        this.dest = destination;
        this.noOfSeats = noOfSeats;
    }

    public String getId() {
        return id;
    }

    public int getOrig() {
        return orig;
    }

    public void setOrig(int orig) {
        this.orig = orig;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int calculateFare() {
        if (noOfSeats == 1) {
            return (dest - orig) * Platform.DEFAULT_FARE;
        } else {
            return (int) ((dest - orig) * noOfSeats * 0.75 * Platform.DEFAULT_FARE);
        }
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id='" + id + '\'' +
                ", orig=" + orig +
                ", dest=" + dest +
                ", noOfSeats=" + noOfSeats +
                ", status=" + status +
                ", driver=" + driver +
                ", rider=" + rider +
                ", fare=" + ((status == Status.BOOKED) ? calculateFare() : 0) +
                '}';
    }
}
