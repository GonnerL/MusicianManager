package com.example.musicianmanager.models;

public class Evaluation {
    private String performerID;
    private int score;
    private String comment;
    private int totalScore;

    @Override
    public String toString() {
        return "Evaluation{" +
                "performerID='" + performerID + '\'' +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                ", totalScore=" + totalScore +
                '}';
    }

    public String getPerformerID() {
        return performerID;
    }

    public void setPerformerID(String performerID) {
        this.performerID = performerID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public Evaluation(String performerID, int score, String comment, int totalScore) {
        this.performerID = performerID;
        this.score = score;
        this.comment = comment;
        this.totalScore = totalScore;
    }
}
