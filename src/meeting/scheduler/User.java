package meeting.scheduler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final Platform p;
    private String name;
    private List<String> meetings;

    User(Platform p, String name) {
        this.p = p;
        this.name = name;
        meetings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<String> meetings) {
        this.meetings = meetings;
    }

    @Override
    public String toString() {
        return "User{" + "p=" + p + ", name='" + name + '\'' + ", meetings=" + meetings + '}';
    }

    public String bookMeeting(LocalDateTime start, LocalDateTime end, int capacity) {
        try {
            String meetingId = p.bookMeeting(name, start, end, capacity);
            meetings.add(meetingId);
            return meetingId;
        } catch (NoRoomAvailableException e) {
            System.out.println("No room available with following capacity and timing.");
            return null;
        }
    }

    public void cancelMeeting(String meetingId) {
        p.cancelMeeting(meetingId);
    }

}
