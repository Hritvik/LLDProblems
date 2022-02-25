package meeting.scheduler;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final List<String> meetings;
    private String name;
    private int capacity;

    Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        meetings = new ArrayList<>();
    }

    public List<String> getMeetings() {
        return meetings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addMeeting(String meetingId) {
        meetings.add(meetingId);
    }

    @Override
    public String toString() {
        return "Room{" + "meetings=" + meetings + ", name='" + name + '\'' + ", capacity=" + capacity + '}';
    }
}
