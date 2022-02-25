package meeting.scheduler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Platform {
    private final Map<String, Meeting> meetings;
    private final Map<String, Room> rooms;
    private final Map<String, User> users;
    public Random random;

    Platform() {
        random = new Random();
        meetings = new HashMap<>();
        rooms = new HashMap<>();
        users = new HashMap<>();
    }

    public static void main(String[] args) {
        Platform p = new Platform();
        p.addRoom("Room A", 5);
        p.addRoom("Room B", 2);
        p.addUser("User A");
        p.addUser("User B");
        User a = p.users.get("User A");
        User b = p.users.get("User B");
        LocalDateTime start = LocalDateTime.of(2020, 2, 14, 9, 0);
        LocalDateTime end = LocalDateTime.of(2020, 2, 14, 10, 0);
        a.bookMeeting(start, end, 3);
        b.bookMeeting(start, end, 4);
        System.out.println(p.rooms);
        System.out.println(p.users);
        System.out.println(p.meetings);

    }

    public String bookMeeting(String userName, LocalDateTime start, LocalDateTime end, int capacity) throws NoRoomAvailableException {
        List<Room> availableRooms = rooms.values().stream().filter(room -> (room.getCapacity() >= capacity) && (room.getMeetings().stream().filter(meetingId -> {
            LocalDateTime s = meetings.get(meetingId).getStart();
            LocalDateTime e = meetings.get(meetingId).getEnd();
            MeetingStatus meetingStatus = meetings.get(meetingId).getMeetingStatus();
            return !(meetingStatus.equals(MeetingStatus.BOOKED) && ((s.isAfter(start) && s.isBefore(end)) || (e.isAfter(start) && e.isBefore(end))));
        }).count() == 0)).collect(Collectors.toList());
        if (availableRooms.size() > 0) {
            Room bookedRoom = availableRooms.get(random.nextInt(availableRooms.size()));
            Meeting meeting = new Meeting(start, end, capacity, bookedRoom.getName());
            meeting.setHost(userName);
            bookedRoom.addMeeting(meeting.getId());
            meetings.put(meeting.getId(), meeting);
            return meeting.getId();
        } else {
            throw new NoRoomAvailableException();
        }
    }

    public void addRoom(String name, int capacity) {
        Room room = new Room(name, capacity);
        rooms.put(room.getName(), room);
    }

    public void addUser(String name) {
        User user = new User(this, name);
        users.put(user.getName(), user);
    }

    public void cancelMeeting(String meetingId) {
        meetings.get(meetingId).setMeetingStatus(MeetingStatus.CANCELLED);
    }

    public enum MeetingStatus {
        BOOKED, CANCELLED
    }
}
