package meeting.scheduler;

import java.time.LocalDateTime;
import java.util.UUID;

public class Meeting {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private String id;
    private String host;
    private int capacity;
    private String roomId;
    private Platform.MeetingStatus meetingStatus;

    public Meeting(LocalDateTime start, LocalDateTime end, int capacity, String roomId) {
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.capacity = capacity;
        this.start = start;
        this.end = end;
        this.roomId = roomId;
        this.meetingStatus = Platform.MeetingStatus.BOOKED;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Platform.MeetingStatus getMeetingStatus() {
        return meetingStatus;
    }

    public void setMeetingStatus(Platform.MeetingStatus meetingStatus) {
        this.meetingStatus = meetingStatus;
    }

    @Override
    public String toString() {
        return "Meeting{" + "start=" + start + ", end=" + end + ", id='" + id + '\'' + ", host='" + host + '\'' + ", capacity=" + capacity + ", roomId='" + roomId + '\'' + ", meetingStatus=" + meetingStatus + '}';
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
