package org.aoc.days.day4;
import java.time.LocalDateTime;
import java.util.Date;

public class Event {
    private LocalDateTime eventTime;
    private String eventType;

    public Event(LocalDateTime eventTime, String eventType) {
        this.eventTime = eventTime;
        this.eventType = eventType;
    }
    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }



}
