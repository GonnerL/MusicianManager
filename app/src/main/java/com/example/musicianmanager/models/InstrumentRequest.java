package com.example.musicianmanager.models;

public class InstrumentRequest {
    private String musicEventID;
    private String instrumentType;
    private String recruitmentNumber;

    public InstrumentRequest(String musicEventID, String instrumentType, String recruitmentNumber) {
        this.musicEventID = musicEventID;
        this.instrumentType = instrumentType;
        this.recruitmentNumber = recruitmentNumber;
    }

    public String getMusicEventID() {
        return musicEventID;
    }

    public void setMusicEventID(String musicEventID) {
        this.musicEventID = musicEventID;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getRecruitmentNumber() {
        return recruitmentNumber;
    }

    public void setRecruitmentNumber(String recruitmentNumber) {
        this.recruitmentNumber = recruitmentNumber;
    }

    @Override
    public String toString() {
        return "InstrumentRequest{" +
                "musicEventID='" + musicEventID + '\'' +
                ", instrumentType='" + instrumentType + '\'' +
                ", recruitmentNumber='" + recruitmentNumber + '\'' +
                '}';
    }
}
