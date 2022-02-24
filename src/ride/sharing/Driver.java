package ride.sharing;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    private final Platform p;
    private final String name;
    private final List<String> rides;

    Driver(String name, Platform p) {
        this.name = name;
        this.p = p;
        rides = new ArrayList<>();
    }

    public void addRide(String id) {
        rides.add(id);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                '}';
    }

    public String toDetailedString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", rides=" + rides +
                '}';
    }
}
