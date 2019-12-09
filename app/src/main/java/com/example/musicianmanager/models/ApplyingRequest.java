package com.example.musicianmanager.models;

public class ApplyingRequest {
    private String musicEventID;
    private String performerID;
    private boolean acceptance;

    public ApplyingRequest(String musicEventID, String performerID, boolean acceptance) {
        this.musicEventID = musicEventID;
        this.performerID = performerID;
        this.acceptance = acceptance;
    }

    public String getMusicEventID() {
        return musicEventID;
    }

    public void setMusicEventID(String musicEventID) {
        this.musicEventID = musicEventID;
    }

    public String getPerformerID() {
        return performerID;
    }

    public void setPerformerID(String performerID) {
        this.performerID = performerID;
    }

    public boolean isAcceptance() {
        return acceptance;
    }

    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }

    @Override
    public String toString() {
        return "ApplyingRequest{" +
                "musicEventID='" + musicEventID + '\'' +
                ", performerID='" + performerID + '\'' +
                ", acceptance=" + acceptance +
                '}';
    }
}
