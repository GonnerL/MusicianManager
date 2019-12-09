package com.example.musicianmanager.models;

public class PerformerRecommendation {
    private String eventType;
    private String careerExperience;
    private int totalScore;

    @Override
    public String toString() {
        return "PerformerRecommendation{" +
                "eventType='" + eventType + '\'' +
                ", careerExperience='" + careerExperience + '\'' +
                ", totalScore=" + totalScore +
                '}';
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getCareerExperience() {
        return careerExperience;
    }

    public void setCareerExperience(String careerExperience) {
        this.careerExperience = careerExperience;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public PerformerRecommendation(String eventType, String careerExperience, int totalScore) {
        this.eventType = eventType;
        this.careerExperience = careerExperience;
        this.totalScore = totalScore;
    }
}
