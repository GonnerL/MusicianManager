package com.example.musicianmanager.models;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Post {

    private String date;
    private String time;
    private String location;
    private String eventType;
    private String hostID;
    private boolean matchedStatus;
    private String contents;
    private String muiscEventId;
    private String title;

    @ServerTimestamp
    private Date serverDate;

    public Post(String date, String time, String location, String eventType, String hostID, boolean matchedStatus, String contents, String muiscEventId, String title) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.eventType = eventType;
        this.hostID = hostID;
        this.matchedStatus = matchedStatus;
        this.contents = contents;
        this.muiscEventId = muiscEventId;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getHostID() {
        return hostID;
    }

    public void setHostID(String hostID) {
        this.hostID = hostID;
    }

    public boolean isMatchedStatus() {
        return matchedStatus;
    }

    public void setMatchedStatus(boolean matchedStatus) {
        this.matchedStatus = matchedStatus;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getMuiscEventId() {
        return muiscEventId;
    }

    public void setMuiscEventId(String muiscEventId) {
        this.muiscEventId = muiscEventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Post{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", eventType='" + eventType + '\'' +
                ", hostID='" + hostID + '\'' +
                ", matchedStatus=" + matchedStatus +
                ", contents='" + contents + '\'' +
                ", muiscEventId='" + muiscEventId + '\'' +
                ", title='" + title + '\'' +
                ", serverDate=" + serverDate +
                '}';
    }
}
