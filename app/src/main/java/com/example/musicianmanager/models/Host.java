package com.example.musicianmanager.models;

public class Host {
    private String hostID;

    public Host(String hostID) {
        this.hostID = hostID;
    }

    public String getHostID() {
        return hostID;
    }

    public void setHostID(String hostID) {
        this.hostID = hostID;
    }

    @Override
    public String toString() {
        return "Host{" +
                "hostID='" + hostID + '\'' +
                '}';
    }
}
