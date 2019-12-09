package com.example.musicianmanager.models;

public class CareerData {
    private String performerID;
    private String name;
    private int gender;
    private int age;
    private String phoneNubmer;
    private String address;
    private String careerExperience;

    public CareerData(String performerID, String name, int gender, int age, String phoneNubmer, String address, String careerExperience) {
        this.performerID = performerID;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phoneNubmer = phoneNubmer;
        this.address = address;
        this.careerExperience = careerExperience;
    }

    @Override
    public String toString() {
        return "CareerData{" +
                "performerID='" + performerID + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", phoneNubmer='" + phoneNubmer + '\'' +
                ", address='" + address + '\'' +
                ", careerExperience='" + careerExperience + '\'' +
                '}';
    }

    public String getPerformerID() {
        return performerID;
    }

    public void setPerformerID(String performerID) {
        this.performerID = performerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNubmer() {
        return phoneNubmer;
    }

    public void setPhoneNubmer(String phoneNubmer) {
        this.phoneNubmer = phoneNubmer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCareerExperience() {
        return careerExperience;
    }

    public void setCareerExperience(String careerExperience) {
        this.careerExperience = careerExperience;
    }
}
